package learning.restful.webservices.springbootrestfulwebservices.registory;

import learning.restful.webservices.springbootrestfulwebservices.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User,Long>{

}
