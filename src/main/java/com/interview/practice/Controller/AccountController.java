package com.interview.practice.Controller;

import com.interview.practice.DTO.*;
import com.interview.practice.Service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody @Valid AccountRequest accountRequest) {
        AccountResponse accountResponse = accountService.createAccount(accountRequest) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(accountResponse) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id) {
        AccountResponse accountResponse = accountService.getAccount(id) ;
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse) ;
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accountResponseList = accountService.getAllAccounts() ;
        return ResponseEntity.status(HttpStatus.OK).body(accountResponseList) ;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @Valid @RequestBody UpdateRequest updateRequest) {
        AccountResponse accountResponse = accountService.updateAccount(id, updateRequest) ;
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse) ;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id) ;
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<AccountResponse> deposit(@PathVariable Long id, @Valid @RequestBody DepositRequest request) {
        AccountResponse accountResponse = accountService.deposit(id, request.getAmount()) ;
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse) ;
    }

    @PostMapping("/{id}/close")
    public ResponseEntity<Void> closeAccount(@PathVariable Long id) {
        accountService.closeAccount(id) ;
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build() ;
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<AccountResponse> withdraw(@PathVariable Long id, @Valid @RequestBody WithDrawalRequest withDrawalRequest) {
        AccountResponse accountResponse = accountService.withdrawal(id, withDrawalRequest.getAmount()) ;
        return ResponseEntity.status(HttpStatus.OK).body(accountResponse) ;

    }

}
