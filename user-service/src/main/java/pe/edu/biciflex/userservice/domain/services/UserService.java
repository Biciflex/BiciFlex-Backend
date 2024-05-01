package pe.edu.biciflex.userservice.domain.services;

import pe.edu.biciflex.userservice.domain.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    List<User> getAll();
    User getByEmail(String email);
    User getById(Long userId);
}
