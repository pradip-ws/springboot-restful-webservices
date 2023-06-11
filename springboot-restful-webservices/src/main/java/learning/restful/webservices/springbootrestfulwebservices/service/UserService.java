package learning.restful.webservices.springbootrestfulwebservices.service;

import learning.restful.webservices.springbootrestfulwebservices.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUser();
    User upadateUser(User user);

    void deleteUser(Long userId);

}
