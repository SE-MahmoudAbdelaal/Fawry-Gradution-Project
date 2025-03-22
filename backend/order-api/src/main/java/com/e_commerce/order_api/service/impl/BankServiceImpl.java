package com.e_commerce.order_api.service.impl;

import com.e_commerce.order_api.feginClient.BankClient;
import com.e_commerce.order_api.model.DepositRequest;
import com.e_commerce.order_api.model.TransactionResponse;
import com.e_commerce.order_api.model.WithdrawRequest;
import com.e_commerce.order_api.service.BankService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BankServiceImpl implements BankService {
    BankClient bankClient;
    @Override
    public void deposit(DepositRequest depositRequest) {
         bankClient.deposit(depositRequest);
    }

    @Override
    public  ResponseEntity<TransactionResponse> withdraw(WithdrawRequest withdrawRequest) {
        return bankClient.withdraw(withdrawRequest);
    }
}
