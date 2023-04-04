package com.food.ordering.system.order.service.domain.ports.input.message.listener.payment;

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse;

/**
 * @author kalvens on 4/3/23
 * @project food-ordering-system
 */

/**
 * Trigger by DomainEvents of PaymentRequest
 */
public interface PaymentResponseMessageListener {
    void paymentCompleted(PaymentResponse paymentResponse);
    void paymentCancelled(PaymentResponse paymentResponse);
}
