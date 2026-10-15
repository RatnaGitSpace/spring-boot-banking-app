package com.interview.practice.Service;

import com.interview.practice.DTO.AccountRequest;
import com.interview.practice.DTO.AccountResponse;
import com.interview.practice.DTO.UpdateRequest;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountResponse createAccount(AccountRequest accountRequest) ;

    AccountResponse getAccount(Long id) ;

    List<AccountResponse> getAllAccounts() ;

    AccountResponse updateAccount(Long id, UpdateRequest updateRequest) ;

    void deleteAccount(Long id) ;

    AccountResponse deposit(Long id, BigDecimal amount) ;

    void closeAccount(Long id) ;

    AccountResponse withdrawal(Long id, BigDecimal amount) ;
}
