package com.e_commerce.order_api.service;

import com.e_commerce.order_api.model.DepositRequest;
import com.e_commerce.order_api.model.TransactionResponse;
import com.e_commerce.order_api.model.WithdrawRequest;
import org.springframework.http.ResponseEntity;

public interface BankService {

    public void deposit(DepositRequest depositRequest);
    public ResponseEntity<TransactionResponse> withdraw(WithdrawRequest transactionRequest);
}
