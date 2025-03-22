package com.e_commerce.order_api.service.impl;

import com.e_commerce.order_api.entity.Item;
import com.e_commerce.order_api.entity.Order;
import com.e_commerce.order_api.exception.CustomException;
import com.e_commerce.order_api.model.DepositRequest;
import com.e_commerce.order_api.model.TransactionResponse;
import com.e_commerce.order_api.model.WithdrawRequest;
import com.e_commerce.order_api.repository.OrderRepository;
import com.e_commerce.order_api.service.BankService;
import com.e_commerce.order_api.service.OrderService;
import feign.FeignException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final BankService bankService;
    private final OrderRepository orderRepo;

    @Transactional
    @Override
    public Order saveOrder(Order order ) {
        WithdrawRequest withdrawRequest = new WithdrawRequest();
        withdrawRequest.setAmount(order.getTotal_amount_after_discount());
        withdrawRequest.setCardNumber(order.getCard_number());
        withdrawRequest.setCvv(order.getCvv());
        ResponseEntity<TransactionResponse> withdrawResponse = bankService.withdraw(withdrawRequest);

        for (Item item : order.getItems()) {
            DepositRequest depositRequest = new DepositRequest();
            depositRequest.setAmount(item.getProduct().getPrice());
            depositRequest.setCardNumber(item.getProduct().getMerchantCardNumber());
            bankService.deposit(depositRequest);
        }
            order.setStatus("PAID");
            order.setTransactionId(withdrawResponse.getBody().getId());
            return orderRepo.save(order);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order findOrderById(Long id) throws Exception {
        return orderRepo.findById(id).orElseThrow(()-> new Exception("Order with ID " + id + " not found"));
    }

    @Override
    public List<Order> findOrderByCustomerId(Long customerId) {
        return orderRepo.findByCustomerId(customerId).orElseThrow(()->new CustomException("Order Not Found" ));
    }

    @Override
    public List<Order> searchOrdersByDateRange(LocalDate startDate, LocalDate endDate , Long customerId) {
        return orderRepo.findByCustomerIdAndCreatedAtBetween(customerId ,startDate, endDate ).orElseThrow(()->new CustomException("No orders found for the specified date range." ));
    }
}
