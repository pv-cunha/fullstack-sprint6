package br.com.rchlo.service;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.PaymentStatus;
import br.com.rchlo.dto.PaymentStatistics;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

class PaymentStatisticsCalculatorTest {
    @Mock
    private PaymentRepository paymentRepository;

    private PaymentStatisticsCalculator paymentStatisticsCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paymentStatisticsCalculator = new PaymentStatisticsCalculator(paymentRepository);

    }

    @Test
    void shouldCalculateMaximumAmountOfConfirmedPayment() {
        Mockito.when(paymentRepository.getMaximumPayment()).thenReturn(new BigDecimal("200.00"));

        PaymentStatistics paymentStatistics = paymentStatisticsCalculator.calculate();

        BigDecimal maximumAmountOfConfirmedPayment = paymentStatistics.getMaximumAmountOfConfirmedPayment();

        Assertions.assertThat(maximumAmountOfConfirmedPayment).isEqualTo(new BigDecimal("200.00"));
    }

    @Test
    void shouldConsiderPaymentAmountByStatus() {
        Map<PaymentStatus, Long> quantityByStatusFake = status();

        Mockito.when(paymentRepository.getListByStatus()).thenReturn(quantityByStatusFake);

        PaymentStatistics paymentStatistics = paymentStatisticsCalculator.calculate();

        Map<PaymentStatus, Long> quantityOfPaymentsByStatus = paymentStatistics.getQuantityOfPaymentsByStatus();

        Assertions.assertThat(quantityOfPaymentsByStatus)
                .containsEntry(PaymentStatus.CREATED, 2L)
                .containsEntry(PaymentStatus.CONFIRMED, 1L)
                .containsEntry(PaymentStatus.CANCELED, 1L);
    }

    private Map<PaymentStatus, Long> status() {
        Map<PaymentStatus, Long> quantityByStatusFake = new HashMap<>();

        quantityByStatusFake.put(PaymentStatus.CREATED, 2L);
        quantityByStatusFake.put(PaymentStatus.CONFIRMED, 1L);
        quantityByStatusFake.put(PaymentStatus.CANCELED, 1L);

        return quantityByStatusFake;
    }

}
