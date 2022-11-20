package com.lounwb.bank.dao;

import com.lounwb.bank.pojo.Account;

import java.util.List;

public interface AccountDao {
    int insert(Account act);
    int deleteById(Long id);
    int update(Account act);
    Account selectByActno(String actno);
    List<Account> selectAll();
}
