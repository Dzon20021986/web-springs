package com.geekbrains.spring.web.order.controllers;


import com.geekbrains.spring.web.order.dto.QiwiResponse;
import com.geekbrains.spring.web.order.services.QiwiService;
import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/v1/paypal")
@RequiredArgsConstructor
@Slf4j
public class QiwiController {
    private final QiwiService qiwiService;

    //ToDo - заменить secretKey на свой, после регистрации на QIWI
    private String secretKey = "a01ccaca02281be3793aa8aa3f18a074";
    private BillPaymentClient client = BillPaymentClientFactory.createDefault(secretKey);

    //Добавить статусы "Оплачен\не оплачен" в наш Ордер
    // В зависимости от успеха операции, менять этот статус
    // Оплаченные заказы не долны быть оплачены дважды

    @GetMapping("/create/{orderId}")
    public QiwiResponse createOrder(@PathVariable Long orderId) throws URISyntaxException {
        BillResponse response = client.createBill(qiwiService.createOrderRequest(orderId));
        log.info("resp = {}", response);
        return new QiwiResponse(response.getPayUrl(), response.getBillId());
    }

    @PostMapping("/capture/{billId}")
    public ResponseEntity<?> captureOrder(@PathVariable String billId) throws IOException {
        BillResponse response = client.getBillInfo(billId);
        if("COMPLETED".equals(response.getStatus())) {
            //ToDo сделать обработку статуса
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
