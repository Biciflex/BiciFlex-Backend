package pe.edu.biciflex.paymentservice.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@With
@Table(name = "payment_methods")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "card_number")
    private UUID cardNumber;
}
