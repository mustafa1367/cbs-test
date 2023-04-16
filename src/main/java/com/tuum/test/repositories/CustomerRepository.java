package com.tuum.test.repositories;

import com.tuum.test.models.Customer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CustomerRepository {
    @Select("select * from customers;")
    public List<Customer> allCustomers();

    @Insert("insert into customers(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int createCustomer(Customer customer);
}
