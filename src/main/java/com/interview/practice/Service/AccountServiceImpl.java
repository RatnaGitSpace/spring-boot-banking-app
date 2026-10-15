package com.interview.practice.Service;

import com.interview.practice.DTO.AccountRequest;
import com.interview.practice.DTO.AccountResponse;
import com.interview.practice.DTO.UpdateRequest;
import com.interview.practice.Entity.Account;
import com.interview.practice.Entity.AccountStatus;
import com.interview.practice.Entity.Transaction;
import com.interview.practice.Exception.AccountClosedException;
import com.interview.practice.Exception.AccountNotFoundException;
import com.interview.practice.Exception.InsufficientBalanceException;
import com.interview.practice.Repo.AccountRepository;
import com.interview.practice.Repo.TransactionRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepo transactionRepo ;

    public AccountServiceImpl(AccountRepository accountRepository, TransactionRepo transactionRepo)
    {
        this.accountRepository = accountRepository ;
        this.transactionRepo = transactionRepo ;
    }

    public AccountResponse createAccount(AccountRequest accountRequest) {

        Account account = new Account() ;

        account.setAccountNumber("ACC" + System.currentTimeMillis()) ;
        account.setCustomerName(accountRequest.getCustomerName()) ;
        account.setAccountType(accountRequest.getAccountType()) ;
        account.setBalance(accountRequest.getInitialDeposit()) ;
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.setCreatedAt(LocalDateTime.now()) ;

        Account savedAccount = accountRepository.save(account) ;

        return mapTOAccountResponse(savedAccount) ;
    }

    public AccountResponse getAccount(Long id) {

        Account account = getAccountEntity(id) ;

        return mapTOAccountResponse(account) ;

    }

    public List<AccountResponse> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll() ;

        List<AccountResponse> accountResponseList = new ArrayList<>();

        for(Account account : accounts) {
            accountResponseList.add(mapTOAccountResponse(account)) ;
        }
        return accountResponseList ;
    }

    public AccountResponse updateAccount(Long id, UpdateRequest updateRequest) {

        Account account = getAccountEntity(id) ;

        if(account.getAccountStatus() == AccountStatus.CLOSED) {
            throw new  AccountClosedException("Closed Accounts cannot be updated") ;
        }

        account.setCustomerName(updateRequest.getCustomerName()) ;
        account.setAccountType(updateRequest.getAccountType()) ;

        Account updatedAccount = accountRepository.save(account) ;

        return mapTOAccountResponse(updatedAccount);
    }

    public void deleteAccount(Long id) {

        Account account = getAccountEntity(id) ;

        accountRepository.delete(account) ;
    }

    @Transactional
    public AccountResponse deposit(Long id, BigDecimal amount) {

        Account account =getAccountEntity(id) ;

        if(account.getAccountStatus() == AccountStatus.CLOSED) {
            throw new AccountClosedException("Transactions are not allowed on closed account") ;
        }

        account.setBalance(account.getBalance().add(amount)) ;

        Account updatedAccount = accountRepository.save(account) ;
        saveTransaction(id, "Deposit", amount, updatedAccount.getBalance());

        return mapTOAccountResponse(updatedAccount) ;
    }

    @Transactional
    public void closeAccount(Long id) {

        Account account = getAccountEntity(id) ;

        if(account.getAccountStatus() == AccountStatus.CLOSED) {
            throw new AccountClosedException("Account is already closed") ;
        }

        account.setAccountStatus(AccountStatus.CLOSED);

        accountRepository.save(account) ;
    }

    @Transactional
    public AccountResponse withdrawal(Long id, BigDecimal amount) {

        Account account =  getAccountEntity(id) ;

        if(account.getAccountStatus() == AccountStatus.CLOSED) {
            throw new AccountClosedException("Transactions are not allowed on closed account") ;
        }

        if(account.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance") ;
        }

        account.setBalance(account.getBalance().subtract(amount)) ;
        Account updatedAccount = accountRepository.save(account) ;

        saveTransaction(id, "Withdraw", amount, updatedAccount.getBalance());

        return mapTOAccountResponse(updatedAccount) ;
    }

    private AccountResponse mapTOAccountResponse(Account account) {

        AccountResponse accountResponse = new AccountResponse() ;

        accountResponse.setId(account.getId()) ;
        accountResponse.setAccountNumber(account.getAccountNumber()) ;
        accountResponse.setCustomerName(account.getCustomerName()) ;
        accountResponse.setAccountType(account.getAccountType()) ;
        accountResponse.setBalance(account.getBalance()) ;
        accountResponse.setAccountStatus(account.getAccountStatus()) ;
        accountResponse.setCreatedAt(account.getCreatedAt()) ;

        return accountResponse ;
    }

    private Account getAccountEntity(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(
                                "Account not found with id : " + id
                        )
                );
    }

    private void saveTransaction(
            Long accountId,
            String type,
            BigDecimal amount,
            BigDecimal balanceAfter
            ) {

        Transaction transaction = new Transaction() ;

        transaction.setAccountId(accountId) ;
        transaction.setType(type) ;
        transaction.setAmount(amount) ;
        transaction.setBalanceAfter(balanceAfter) ;
        transaction.setCreatedAt(LocalDateTime.now()) ;

        transactionRepo.save(transaction) ;

    }
}
