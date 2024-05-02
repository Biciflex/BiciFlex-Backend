package pe.edu.biciflex.bikeservice.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.biciflex.bikeservice.domain.model.Bike;

@Repository
public interface BikeRepository extends JpaRepository<Bike, Long> {

}
