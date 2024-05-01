package pe.edu.biciflex.rentalservice.domain.services;


import pe.edu.biciflex.rentalservice.domain.model.Rental;

import java.util.List;

public interface RentalService {
    Rental create(Rental bike);
    List<Rental> getAll();
    Rental getById(Long bikeId);
}
