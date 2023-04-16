package com.tuum.test.repositories;


import com.tuum.test.models.Account;
import com.tuum.test.models.Balance;
import com.tuum.test.models.Currency;
import com.tuum.test.viewmodels.AccountOutputVM;
import com.tuum.test.viewmodels.BalanceOutputVM;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AccountRepository {
    @Insert("insert into accounts (customer_id, country) values(#{customer_id}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int createAccount(Account account);

    @Insert("insert into balances(account_id, currency_id, available_amount) values(#{account_id}, #{currency_id}, #{available_amount})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int setBalance(Balance balance);

    @Select("select * from currencies where code = #{code}")
    public Currency getCurrencyByCode(String code);

    @Select("select b.available_amount as availableBalance, c.code as currency from balances b inner join currencies c on c.id = b.currency_id where b.account_id = #{accountId}")
    public List<BalanceOutputVM> getBalanceByAccount(int accountId);

    @Select("select id as accountId, customer_id as customerId from accounts where id = #{id}")
    public AccountOutputVM getAccountById(int id);

    @Update("update balances set available_amount = available_amount + #{amount} where account_id=#{accountId} and currency_id = #{currencyId}")
    public int increaseBalanceAmountByAccountId(int accountId, int currencyId, int amount);

    @Update("update balances set available_amount = available_amount - #{amount} where account_id=#{accountId} and currency_id = #{currencyId}")
    public int decreaseBalanceAmountByAccountId(int accountId, int currencyId, int amount);

    @Select("select * from balances where account_id = #{accountId} and currency_id = #{currencyId}")
    public Balance getBalance(int accountId, int currencyId);
}
