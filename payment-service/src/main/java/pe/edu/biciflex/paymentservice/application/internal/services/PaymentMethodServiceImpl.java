package pe.edu.biciflex.paymentservice.application.internal.services;

import org.springframework.stereotype.Service;
import pe.edu.biciflex.paymentservice.domain.models.PaymentMethod;
import pe.edu.biciflex.paymentservice.domain.services.PaymentMethodService;
import pe.edu.biciflex.paymentservice.infrastructure.persistence.PaymentMethodRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;

    public PaymentMethodServiceImpl(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @Override
    public PaymentMethod create(PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @Override
    public List<PaymentMethod> getAll() {
        return paymentMethodRepository.findAll();
    }



    @Override
    public Optional<PaymentMethod> getById(Long paymentMethodId) {
        return paymentMethodRepository.findById(paymentMethodId);
    }

    @Override
    public Optional<PaymentMethod> getByCardNumber(UUID cardNumber) {
        return paymentMethodRepository.findByCardNumber(cardNumber);
    }
}
