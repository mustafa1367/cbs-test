package com.tuum.test.services;

import com.tuum.test.models.Account;
import com.tuum.test.models.Balance;
import com.tuum.test.repositories.AccountRepository;
import com.tuum.test.viewmodels.AccountInputVM;
import com.tuum.test.viewmodels.AccountOutputVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private Logger logger = LoggerFactory.getLogger(AccountService.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public ResponseEntity getAccountById(int id) {
        var account = accountRepository.getAccountById(id);
        if (account == null) {
            return new ResponseEntity("Account Not Found", HttpStatus.NOT_FOUND);
        }
        account.setBalances(accountRepository.getBalanceByAccount(account.getAccountId()));

        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    public ResponseEntity createAccount(AccountInputVM accountInputVM) {
        boolean hasInvalidCurrency = accountInputVM.getCurrencies().stream()
                .anyMatch(x -> accountRepository.getCurrencyByCode(x) == null);

        if (hasInvalidCurrency) {
            return new ResponseEntity<>("Invalid currency", HttpStatus.BAD_REQUEST);
        }

        var account = new Account();
        account.setCustomer_id(accountInputVM.getCustomerId());
        account.setCountry(accountInputVM.getCountry());
        accountRepository.createAccount(account);

        accountInputVM.getCurrencies().forEach(x -> {
            var currency = accountRepository.getCurrencyByCode(x);
            var balance = new Balance();
            balance.setAccount_id(account.getId());
            balance.setCurrency_id(currency.getId());
            balance.setAvailable_amount(0);
            accountRepository.setBalance(balance);
        });

        var accountOutputVM = new AccountOutputVM();
        accountOutputVM.setAccountId(account.getId());
        accountOutputVM.setCustomerId(accountInputVM.getCustomerId());
        accountOutputVM.setBalances(accountRepository.getBalanceByAccount(account.getId()));
        rabbitTemplate.convertAndSend(accountOutputVM);
        return new ResponseEntity(accountOutputVM, HttpStatus.OK);
    }
}
