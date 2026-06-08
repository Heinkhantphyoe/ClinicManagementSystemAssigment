package clinicmanagementsystem.model;

public class Payment {
    private String paymentId;
    private String patientId;
    private double amount;
    private String paymentDate;
    private String paymentMethod;
    private String status;

    public Payment() {
    }

    public Payment(String paymentId, String patientId, double amount, String paymentDate,
            String paymentMethod, String status) {
        this.paymentId = paymentId;
        this.patientId = patientId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return paymentId + "," + patientId + "," + amount + "," + paymentDate + ","
                + paymentMethod + "," + status;
    }
}
