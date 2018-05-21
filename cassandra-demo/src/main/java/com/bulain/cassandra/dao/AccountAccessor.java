package com.bulain.cassandra.dao;

import com.bulain.cassandra.pojo.Account;
import com.bulain.cassandra.pojo.Address;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;

@Accessor
public interface AccountAccessor {

    @Query("SELECT * FROM complex.accounts WHERE email = :email")
    Account get(@Param("email") String email);

    @Query("SELECT * FROM complex.accounts where name = :name")
    Result<Account> find(@Param("name") String name);

    @Query("INSERT INTO complex.accounts (email, name, addr) values (:email, :name, :addr)")
    ResultSet insert(@Param("email") String email, @Param("name") String name, @Param("addr") Address addr);

    @Query("UPDATE complex.accounts SET name=:name, addr=:addr  WHERE email = :email")
    ResultSet update(@Param("email") String email, @Param("name") String name, @Param("addr") Address addr);

    @Query("DELETE FROM complex.accounts WHERE email = :email")
    ResultSet delete(@Param("email") String email);

}
