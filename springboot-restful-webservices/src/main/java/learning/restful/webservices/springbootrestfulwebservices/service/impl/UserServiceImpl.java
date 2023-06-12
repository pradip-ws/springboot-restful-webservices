package learning.restful.webservices.springbootrestfulwebservices.service.impl;

import learning.restful.webservices.springbootrestfulwebservices.dto.UserDTO;
import learning.restful.webservices.springbootrestfulwebservices.entity.User;
import learning.restful.webservices.springbootrestfulwebservices.exception.EmailAlreadyExistException;
import learning.restful.webservices.springbootrestfulwebservices.exception.ResourceNotFoundException;
import learning.restful.webservices.springbootrestfulwebservices.mapper.AutoUserMapper;
import learning.restful.webservices.springbootrestfulwebservices.mapper.UserMapper;
import learning.restful.webservices.springbootrestfulwebservices.registory.UserRepository;
import learning.restful.webservices.springbootrestfulwebservices.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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

    ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDto) {
        //convert UserDto into User JPA Entity
    //  User user = UserMapper.mapToUser(userDto);
//      User user = modelMapper.map(userDto,User.class);
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistException("Email Already Exist For User");
        }
        User savedUser = userRepository.save(user);
        //Convert User JPA Entity to UserDto
//      UserDTO  savedUserDto = UserMapper.maptoUserDTO(savedUser);
//      UserDTO  savedUserDto = modelMapper.map(savedUser,UserDTO.class);
        UserDTO  savedUserDto = AutoUserMapper.MAPPER.maptoUserDTO(savedUser);
        return savedUserDto;
    }

    @Override
    public UserDTO getUserById(Long id) {
       User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));

//      return UserMapper.maptoUserDTO(user.get());
//      return modelMapper.map(user.get(),UserDTO.class);
        return AutoUserMapper.MAPPER.maptoUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
//      return userRepository.findAll().stream().map(UserMapper::maptoUserDTO).collect(Collectors.toList());
//      return userRepository.findAll().stream().map(user -> modelMapper.map(user,UserDTO.class)).collect(Collectors.toList());
        return userRepository.findAll().stream().map(user -> AutoUserMapper.MAPPER.maptoUserDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserDTO upadateUser(UserDTO userDto) {
        User existinglUser = userRepository.findById(userDto.getId()).orElseThrow(()-> new ResourceNotFoundException("User","id",userDto.getId()));
        existinglUser.setFirstName(userDto.getFirstName());
        existinglUser.setLastName(userDto.getLastName());
        existinglUser.setEmail(userDto.getEmail());

        User savedUser = userRepository.save(existinglUser);
//      return UserMapper.maptoUserDTO(savedUser);
        return AutoUserMapper.MAPPER.maptoUserDTO(savedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        User existinglUser = userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User","id",userId)
        );
        userRepository.deleteById(existinglUser.getId());
    }
}
