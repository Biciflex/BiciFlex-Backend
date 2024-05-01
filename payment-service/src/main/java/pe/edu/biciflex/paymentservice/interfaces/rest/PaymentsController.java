package pe.edu.biciflex.paymentservice.interfaces.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.biciflex.paymentservice.application.internal.outboundservices.ExternalRentalService;
import pe.edu.biciflex.paymentservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.biciflex.paymentservice.domain.models.Payment;
import pe.edu.biciflex.paymentservice.domain.models.PaymentMethod;
import pe.edu.biciflex.paymentservice.domain.services.PaymentMethodService;
import pe.edu.biciflex.paymentservice.domain.services.PaymentService;
import pe.edu.biciflex.paymentservice.interfaces.rest.resources.PaymentMethodResource;
import pe.edu.biciflex.paymentservice.interfaces.rest.resources.PaymentResource;
import pe.edu.biciflex.paymentservice.interfaces.rest.resources.RequestPaymentResource;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    private final PaymentService paymentService;
    private final PaymentMethodService paymentMethodService;
    private final ExternalRentalService externalRentalService;

    public PaymentsController(PaymentService paymentService, PaymentMethodService paymentMethodService, ExternalRentalService externalRentalService) {
        this.paymentService = paymentService;
        this.paymentMethodService = paymentMethodService;
        this.externalRentalService = externalRentalService;
    }

    @PostMapping("/pay/{rentalId}")
    public ResponseEntity<Payment> createPayment(@PathVariable Long rentalId, @RequestBody RequestPaymentResource resource) {

        var rentalResponse = externalRentalService.getRentalById(rentalId);
        if(rentalResponse.getStatusCode() != HttpStatus.OK)
            throw new ResourceNotFoundException("Rental", rentalId);

        Optional<PaymentMethod> paymentMethod = paymentMethodService.getByCardNumber(resource.getCardNumber());

        if(paymentMethod.isPresent()) {
            Payment payment = new Payment()
                    .withRentalId(rentalId)
                    .withPaymentMethod(paymentMethod.get())
                    .withPaymentDate(LocalDateTime.now())
                    .withAmountPaid(rentalResponse.getBody().getTotalAmount());

            return new ResponseEntity<>(paymentService.create(payment), HttpStatus.CREATED);

        }

        PaymentMethod newPaymentMethod = paymentMethodService.create(new PaymentMethod().withName(resource.getName()).withCardNumber(resource.getCardNumber()));

        Payment payment = new Payment()
                .withRentalId(rentalId)
                .withPaymentMethod(newPaymentMethod)
                .withPaymentDate(LocalDateTime.now())
                .withAmountPaid(rentalResponse.getBody().getTotalAmount());


        return new ResponseEntity<>(paymentService.create(payment), HttpStatus.CREATED);
    }


    @GetMapping("{paymentId}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long paymentId) {

        Payment payment = paymentService.getById(paymentId);

        var rentalResponse = externalRentalService.getRentalById(payment.getRentalId());
        if(rentalResponse.getStatusCode() != HttpStatus.OK)
            throw new ResourceNotFoundException("Rental", payment.getRentalId());

        PaymentMethod paymentMethod = paymentMethodService.getById(payment.getPaymentMethod().getId()).get();
        PaymentMethodResource paymentMethodResource = new PaymentMethodResource(paymentMethod.getId(), paymentMethod.getName(),paymentMethod.getCardNumber());

        PaymentResource paymentResource = new PaymentResource(payment.getId(),rentalResponse.getBody(),paymentMethodResource,payment.getPaymentDate(), payment.getAmountPaid());
        return new ResponseEntity<>(paymentResource, HttpStatus.OK);
    }
}
