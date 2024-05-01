package pe.edu.biciflex.paymentservice.interfaces.rest.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.edu.biciflex.paymentservice.application.internal.outboundservices.resources.RentalResource;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentResource {
    private Long id;
    private RentalResource rental;
    private PaymentMethodResource paymentMethod;
    private LocalDateTime paymentDate;
    private Double amountPaid;
}
