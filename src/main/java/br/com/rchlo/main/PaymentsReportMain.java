package br.com.rchlo.main;

import br.com.rchlo.data.PaymentRepository;
import br.com.rchlo.service.PaymentStatisticsCalculator;

public class PaymentsReportMain {

    public static void main(String[] args) {
        var paymentRepository = new PaymentRepository();
        var paymentStatisticsCalculator = new PaymentStatisticsCalculator(paymentRepository);

       paymentStatisticsCalculator.calculate();
    }



}
