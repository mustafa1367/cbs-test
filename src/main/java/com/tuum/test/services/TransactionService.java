package com.tuum.test.services;

import com.tuum.test.models.Transaction;
import com.tuum.test.repositories.AccountRepository;
import com.tuum.test.repositories.TransactionRepository;
import com.tuum.test.viewmodels.TransactionInputVM;
import com.tuum.test.viewmodels.TransactionOutputVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    public ResponseEntity create(TransactionInputVM transactionInputVM) {

        if (!transactionInputVM.getDirection().equals("IN") && !transactionInputVM.getDirection().equals("OUT")) {
            return new ResponseEntity<>("Direction should be either IN or OUT", HttpStatus.BAD_REQUEST);
        }
        if (isNullOrEmpty(transactionInputVM.getDescription())) {
            return new ResponseEntity<>("Description Missing", HttpStatus.BAD_REQUEST);
        }
        var account = accountRepository.getAccountById(transactionInputVM.getAccountId());
        if (account == null) {
            return new ResponseEntity<>("Account Missing", HttpStatus.BAD_REQUEST);
        }
        var currency = accountRepository.getCurrencyByCode(transactionInputVM.getCurrency());
        if(currency == null) {
            return new ResponseEntity<>("Invalid Currency", HttpStatus.BAD_REQUEST);
        }
        if (transactionInputVM.getAmount() <= 0 ) {
            return new ResponseEntity<>("Invalid amount", HttpStatus.BAD_REQUEST);
        }
        var balance = accountRepository.getBalance(account.getAccountId(), currency.getId());
        if (balance == null) {
            return new ResponseEntity<>("Account with given currency was not found", HttpStatus.NOT_FOUND);
        } else if ((balance.getAvailable_amount() < transactionInputVM.getAmount()) && transactionInputVM.getDirection().equals("OUT")) {
            return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
        }
        var transaction = new Transaction();
        transaction.setAmount(transactionInputVM.getAmount());
        transaction.setDirection(transactionInputVM.getDirection());
        transaction.setDescription(transactionInputVM.getDescription());
        transaction.setCurrency_id(currency.getId());
        transaction.setAccount_id(account.getAccountId());
        transactionRepository.create(transaction);

        if(transactionInputVM.getDirection().equals("IN"))
            accountRepository.increaseBalanceAmountByAccountId(account.getAccountId(),currency.getId(),transaction.getAmount());
        else if(transactionInputVM.getDirection().equals("OUT"))
            accountRepository.decreaseBalanceAmountByAccountId(account.getAccountId(),currency.getId(),transaction.getAmount());

        var newBalance = accountRepository.getBalance(account.getAccountId(), currency.getId());

        var output = new TransactionOutputVM();
        output.setDirection(transaction.getDirection());
        output.setAmount(transaction.getAmount());
        output.setCurrency(currency.getCode());
        output.setDescription(transaction.getDescription());
        output.setAccountId(account.getAccountId());
        output.setId(transaction.getId());

        output.setBalanceAfterTransaction(newBalance.getAvailable_amount());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    public ResponseEntity getTransactionsForAccount(int accountId) {
        var account = accountRepository.getAccountById(accountId);
        if(account == null) {
            return new ResponseEntity<>("Invalid Account", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transactionRepository.getTransaction(accountId), HttpStatus.OK);
    }

    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }
}
