package br.com.rchlo.service;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.domain.Payment;
import br.com.rchlo.domain.PaymentStatus;
import br.com.rchlo.dto.PaymentStatistics;

import java.math.BigDecimal;
import java.util.Map;

public class PaymentStatisticsCalculator {

    private final PaymentRepository paymentRepository;

    public PaymentStatisticsCalculator(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentStatistics calculate() {
        PaymentStatistics paymentStatistics = new PaymentStatistics(this.paymentRepository);

        showPaymentStatistic(paymentStatistics);

        return paymentStatistics;
    }

    private static void showPaymentStatistic(PaymentStatistics paymentStatistics) {
        System.out.printf("%nMaior pagamento confirmado %.2f %n", paymentStatistics.getMaximumAmountOfConfirmedPayment());

        Map<PaymentStatus, Long> quantidadeDePagamentoPorStatus = paymentStatistics.getQuantityOfPaymentsByStatus();

        for (PaymentStatus paymentStatus : quantidadeDePagamentoPorStatus.keySet()) {
            System.out.printf("Quantidade de pagamentos com status '%s': %d %n", paymentStatus, quantidadeDePagamentoPorStatus.get(paymentStatus));
        }
    }

    private boolean isMaximumPayment(BigDecimal maximumConfirmedPayment, Payment payment, PaymentStatus paymentStatus) {
        return paymentStatus.equals(payment.getStatus()) && maximumConfirmedPayment.compareTo(payment.getAmount()) < 0;
    }

}
