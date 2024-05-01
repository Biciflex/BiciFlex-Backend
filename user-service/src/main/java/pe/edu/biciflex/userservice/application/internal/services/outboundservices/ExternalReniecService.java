package pe.edu.biciflex.userservice.application.internal.services.outboundservices;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.edu.biciflex.userservice.application.internal.services.outboundservices.resources.ResponseValidationResource;
import pe.edu.biciflex.userservice.domain.model.User;
import pe.edu.biciflex.userservice.application.internal.services.outboundservices.resources.RequestPersonValidationResource;

@Service
public class ExternalReniecService {

    private static final String URL = "https://leadyourway-reniec.azurewebsites.net/api/v1/consultations";

    private final RestTemplate restTemplate;

    public ExternalReniecService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public ResponseValidationResource validatePerson(User user) {
        RequestPersonValidationResource request = new RequestPersonValidationResource(user.getFirstName(), user.getLastName(), user.getDni());

        return restTemplate.postForObject(URL + "/validate",request, ResponseValidationResource.class );
    }
}
