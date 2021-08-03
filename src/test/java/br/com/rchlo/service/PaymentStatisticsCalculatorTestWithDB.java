package br.com.rchlo.service;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.Card;
import br.com.rchlo.domain.Payment;
import br.com.rchlo.domain.PaymentStatus;
import br.com.rchlo.dto.PaymentStatistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class PaymentStatisticsCalculatorTestWithDB {
    private PaymentStatisticsCalculator paymentStatisticsCalculator;
    private PaymentStatistics paymentStatistics;
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        paymentStatisticsCalculator = new PaymentStatisticsCalculator(paymentRepository);
        paymentStatistics = paymentStatisticsCalculator.calculate();
    }

    @Test
    void shouldCalculateMaximumAmountOfConfirmedPayment() {
        BigDecimal maximumAmountOfConfirmedPayment = paymentStatistics.getMaximumAmountOfConfirmedPayment();
        Assertions.assertThat(maximumAmountOfConfirmedPayment).isEqualTo(new BigDecimal("200.00"));
    }

    @Test
    void shouldReturnQuantityGroupedByStatus() {
        Map<PaymentStatus, Long> quantityOfPaymentsByStatus = paymentStatistics.getQuantityOfPaymentsByStatus();
        Assertions.assertThat(quantityOfPaymentsByStatus)
                .containsEntry(PaymentStatus.CREATED, 2L)
                .containsEntry(PaymentStatus.CONFIRMED, 1L)
                .containsEntry(PaymentStatus.CANCELED, 1L);
    }

}
