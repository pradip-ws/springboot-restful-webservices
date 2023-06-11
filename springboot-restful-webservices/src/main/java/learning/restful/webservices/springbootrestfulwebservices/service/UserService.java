package learning.restful.webservices.springbootrestfulwebservices.service;

import learning.restful.webservices.springbootrestfulwebservices.dto.UserDTO;
import learning.restful.webservices.springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUser();
    UserDTO upadateUser(UserDTO user);

    void deleteUser(Long userId);

}
