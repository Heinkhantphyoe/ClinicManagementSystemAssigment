package clinicmanagementsystem.service;

import clinicmanagementsystem.model.Appointment;
import clinicmanagementsystem.model.Payment;
import java.util.List;

public class ReportService {
    public int countAppointments(List<Appointment> appointments) {
        return appointments.size();
    }

    public double calculateTotalPayments(List<Payment> payments) {
        double total = 0.0;
        for (Payment payment : payments) {
            total += payment.getAmount();
        }
        return total;
    }
}
