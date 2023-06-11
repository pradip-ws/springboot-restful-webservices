package learning.restful.webservices.springbootrestfulwebservices.mapper;

import learning.restful.webservices.springbootrestfulwebservices.dto.UserDTO;
import learning.restful.webservices.springbootrestfulwebservices.entity.User;

public class UserMapper {
    public static UserDTO maptoUserDTO(User user){
        UserDTO  userDTO = new UserDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
        return userDTO;
    }

    public static User mapToUser(UserDTO userDto){
        User user = new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
        return user;
    }
}
