package learning.restful.webservices.springbootrestfulwebservices.registory;

import learning.restful.webservices.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User,Long>{
        //Write Spring Data JPA Custom Query Method
        Optional<User> findByEmail(String email);
}
