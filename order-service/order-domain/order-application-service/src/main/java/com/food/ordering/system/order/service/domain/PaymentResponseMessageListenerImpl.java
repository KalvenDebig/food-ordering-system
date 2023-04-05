package com.food.ordering.system.order.service.domain;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.payment.PaymentResponseMessageListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * @author kalvens on 4/4/23
 * @project food-ordering-system
 */

/**
 * <p>This class will be triggered by other domain events</p>
 * <p>Other two helper class will handle two methods here to complete saga pattern</p>
 */
@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class PaymentResponseMessageListenerImpl implements PaymentResponseMessageListener {

    @Override
    public void paymentCompleted(PaymentResponse paymentResponse) {

    }

    @Override
    public void paymentCancelled(PaymentResponse paymentResponse) {

    }
}
