package com.e_commerce.order_api.feginClient;

import com.e_commerce.order_api.model.DepositRequest;
import com.e_commerce.order_api.model.TransactionResponse;
import com.e_commerce.order_api.model.WithdrawRequest;
import com.e_commerce.order_api.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bank-api", url = "http://localhost:8085/api/transactions",
            configuration = FeignConfig.class)
public interface BankClient {
    @PostMapping(value = "/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody DepositRequest depositRequest);
    @PostMapping(value = "/withdraw")
    public  ResponseEntity<TransactionResponse> withdraw(@RequestBody WithdrawRequest withdrawRequest);
}
