package pe.edu.biciflex.paymentservice.domain.services;

import pe.edu.biciflex.paymentservice.domain.models.Payment;

import java.util.List;

public interface PaymentService {
    Payment create(Payment payment);
    List<Payment> getAll();
    Payment getById(Long paymentId);
}
