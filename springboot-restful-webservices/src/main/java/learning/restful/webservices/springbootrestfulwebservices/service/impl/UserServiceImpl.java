package learning.restful.webservices.springbootrestfulwebservices.service.impl;

import learning.restful.webservices.springbootrestfulwebservices.dto.UserDTO;
import learning.restful.webservices.springbootrestfulwebservices.entity.User;
import learning.restful.webservices.springbootrestfulwebservices.mapper.UserMapper;
import learning.restful.webservices.springbootrestfulwebservices.registory.UserRepository;
import learning.restful.webservices.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDto) {
        //convert UserDto into User JPA Entity
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        //Convert User JPA Entity to UserDto
        UserDTO  savedUserDto = UserMapper.maptoUserDTO(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDTO getUserById(Long id) {
       Optional<User> user = userRepository.findById(id);
       return UserMapper.maptoUserDTO(user.get());
    }

    @Override
    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream().map(UserMapper::maptoUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO upadateUser(UserDTO userDto) {
        User existinglUser = userRepository.findById(userDto.getId()).get();
        existinglUser.setFirstName(userDto.getFirstName());
        existinglUser.setLastName(userDto.getLastName());
        existinglUser.setEmail(userDto.getEmail());

        User savedUser = userRepository.save(existinglUser);
        return UserMapper.maptoUserDTO(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
