package br.com.rchlo.dto;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.PaymentStatus;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentStatistics {
//    Liskov Substitution Principle

    private PaymentRepository paymentRepository;

    public PaymentStatistics(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public BigDecimal getMaximumAmountOfConfirmedPayment() {
        return this.paymentRepository.getMaximumPayment();
    }

    public Map<PaymentStatus, Long> getQuantityOfPaymentsByStatus() {
        return this.paymentRepository.getListByStatus();
    }

}
