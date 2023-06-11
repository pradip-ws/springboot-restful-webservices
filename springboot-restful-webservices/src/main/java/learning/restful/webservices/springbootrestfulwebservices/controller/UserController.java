package learning.restful.webservices.springbootrestfulwebservices.controller;

import learning.restful.webservices.springbootrestfulwebservices.dto.UserDTO;
import learning.restful.webservices.springbootrestfulwebservices.entity.User;
import learning.restful.webservices.springbootrestfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@ResponseBody
//@Controller
@RequestMapping("api")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("users")
    public ResponseEntity<List<UserDTO>> getAllUserById(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long userId,@RequestBody UserDTO user){
        user.setId(userId);
        return ResponseEntity.ok(userService.upadateUser(user));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User Successfully Deleted");
    }
}
