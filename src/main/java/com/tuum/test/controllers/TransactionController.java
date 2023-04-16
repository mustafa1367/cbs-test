package com.tuum.test.controllers;

import com.tuum.test.services.TransactionService;
import com.tuum.test.viewmodels.TransactionInputVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @PostMapping()
    public ResponseEntity create(@RequestBody TransactionInputVM transactionInputVM) {
        return transactionService.create(transactionInputVM);
    }
    @GetMapping("{accountId}")
    public ResponseEntity transactions(@PathVariable String accountId) {
        return transactionService.getTransactionsForAccount(Integer.parseInt(accountId));
    }
}
