package pe.edu.biciflex.rentalservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.biciflex.rentalservice.domain.model.Rental;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {

}
