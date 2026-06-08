package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private final List<Payment> payments = new ArrayList<Payment>();

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<Payment>(payments);
    }

    public Payment findById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }
}
