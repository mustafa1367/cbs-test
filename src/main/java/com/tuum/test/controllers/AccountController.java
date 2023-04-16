package com.tuum.test.controllers;

import com.tuum.test.services.AccountService;
import com.tuum.test.viewmodels.AccountInputVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping()
    public ResponseEntity createAccount(@RequestBody AccountInputVM accountInputVM) {
        return accountService.createAccount(accountInputVM);
    }

    @GetMapping()
    public ResponseEntity getAccount(@RequestParam int id) {
        return accountService.getAccountById(id);
    }

}
