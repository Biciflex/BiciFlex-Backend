package pe.edu.biciflex.bikeservice.application.internal.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.edu.biciflex.bikeservice.domain.exceptions.ResourceNotFoundException;
import pe.edu.biciflex.bikeservice.domain.model.Bike;
import pe.edu.biciflex.bikeservice.domain.services.BikeService;
import pe.edu.biciflex.bikeservice.infrastructure.persistence.BikeRepository;

import java.util.List;

@Service
@Slf4j
public class BikeServiceImpl implements BikeService {

    private static final String ENTITY = "Bike";

    private final BikeRepository bikeRepository;

    public BikeServiceImpl(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public Bike create(Bike bike) {
        bike.setId(null);
        bike.setStatus("Available");
        return bikeRepository.save(bike);
    }

    @Override
    public List<Bike> getAll() {
        return bikeRepository.findAll();
    }

    @Override
    public Bike getById(Long bikeId) {
        return bikeRepository.findById(bikeId).orElseThrow(() -> new ResourceNotFoundException(ENTITY, bikeId));
    }
}
