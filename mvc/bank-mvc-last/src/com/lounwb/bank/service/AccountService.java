package com.lounwb.bank.service;

import com.lounwb.bank.exceptions.AppException;
import com.lounwb.bank.exceptions.MoneyNotEnoughException;

public interface AccountService {
    void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException;
}
