package com.tuum.test.repositories;

import com.tuum.test.models.Transaction;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TransactionRepository {

    @Insert("insert into transactions(account_id,amount,currency_id,direction,description) values(#{account_id}, #{amount}, #{currency_id}, #{direction}, #{description})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int create(Transaction transaction);

    @Select("select * from transactions where account_id = #{accountId}")
    public List<Transaction> getTransaction(int accountId);
}
