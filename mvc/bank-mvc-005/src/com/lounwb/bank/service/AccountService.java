package com.lounwb.bank.service;

import com.lounwb.bank.dao.AccountDao;
import com.lounwb.bank.pojo.Account;
import com.lounwb.bank.exceptions.AppException;
import com.lounwb.bank.exceptions.MoneyNotEnoughException;
import com.lounwb.bank.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService {
    private AccountDao dao = new AccountDao();

    public void transfer(String fromActno, String toActno, double money) throws MoneyNotEnoughException, AppException {
        try(Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);
            Account fromAct = dao.selectByActno(fromActno);
            if(fromAct.getBalance() < money){
                throw new MoneyNotEnoughException("余额不足");
            }
            Account toAct = dao.selectByActno(toActno);

            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);

            int count = dao.update(fromAct);

            //模拟异常
            /*String s = null;
            s.toString();*/

            count += dao.update(toAct);

            conn.commit();
            if (count != 2) {
                throw new AppException("账户转账异常!!!");
            }
        } catch (Exception e) {
            throw new AppException("账户转账异常!!!");
        }

    }
}
