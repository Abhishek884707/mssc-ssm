package org.mourya.msscssm.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mourya.msscssm.domain.Payment;
import org.mourya.msscssm.domain.PaymentEvent;
import org.mourya.msscssm.domain.PaymentState;
import org.mourya.msscssm.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp(){
        payment = Payment.builder().amount(new BigDecimal("12.99")).build();
    }

    @Test
    void preAuth(){
        Payment savedPayment = paymentService.newPayment(payment);

        StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());

        paymentService.preAuth(savedPayment.getId());

        Optional<Payment> preAuthPayment = paymentRepository.findById(savedPayment.getId());

        System.out.println(sm.getState().getId());

        System.out.println(preAuthPayment);
    }
}