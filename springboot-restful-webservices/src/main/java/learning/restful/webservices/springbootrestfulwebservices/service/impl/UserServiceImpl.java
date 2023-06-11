package learning.restful.webservices.springbootrestfulwebservices.service.impl;

import learning.restful.webservices.springbootrestfulwebservices.entity.User;
import learning.restful.webservices.springbootrestfulwebservices.registory.UserRepository;
import learning.restful.webservices.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
       Optional<User> user = userRepository.findById(id);
       return user.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User upadateUser(User user) {
        User existinglUser = userRepository.findById(user.getId()).get();
        existinglUser.setFirstName(user.getFirstName());
        existinglUser.setLastName(user.getLastName());
        existinglUser.setEmail(user.getEmail());

        User savedUser = userRepository.save(existinglUser);
        return savedUser;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
