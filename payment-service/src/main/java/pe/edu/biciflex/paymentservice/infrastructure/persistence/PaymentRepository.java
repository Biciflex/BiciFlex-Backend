package pe.edu.biciflex.paymentservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.biciflex.paymentservice.domain.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
