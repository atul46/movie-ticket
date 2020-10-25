package com.superops.tickets.payments.impl;

import com.superops.tickets.payments.PaymentService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String makePayment() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
           throw new RuntimeException(e.getMessage());
        }
        return "SUCCESS";
    }
}
