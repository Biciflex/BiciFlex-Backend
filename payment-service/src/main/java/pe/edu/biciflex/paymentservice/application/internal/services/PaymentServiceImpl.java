package pe.edu.biciflex.paymentservice.application.internal.services;

import org.springframework.stereotype.Service;
import pe.edu.biciflex.paymentservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.biciflex.paymentservice.domain.models.Payment;
import pe.edu.biciflex.paymentservice.domain.services.PaymentService;
import pe.edu.biciflex.paymentservice.infrastructure.persistence.PaymentRepository;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final String ENTITY = "Payment";
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment create(Payment payment) {
        payment.setId(null);
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment getById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, paymentId));
    }
}
