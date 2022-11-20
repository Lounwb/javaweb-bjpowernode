package com.lounwb.bank.web;

import com.lounwb.bank.exceptions.AppException;
import com.lounwb.bank.exceptions.MoneyNotEnoughException;
import com.lounwb.bank.service.AccountService;
import com.lounwb.bank.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 账户小程序
 * @author lounwb
 * @version 2.0
 * @since 1.0
 */
@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromActno = request.getParameter("fromActno");
        String toActno = request.getParameter("toActno");
        double monty  = Double.parseDouble(request.getParameter("money"));

        AccountService service = new AccountServiceImpl();
        try {
            service.transfer(fromActno, toActno, monty);
            response.sendRedirect(request.getContextPath() + "/success.jsp");
        } catch (MoneyNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/moneynotenough.jsp");
        } catch (AppException e) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

}
