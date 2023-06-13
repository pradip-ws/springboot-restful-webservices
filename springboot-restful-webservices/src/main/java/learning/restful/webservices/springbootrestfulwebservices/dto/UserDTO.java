package learning.restful.webservices.springbootrestfulwebservices.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;

    //user fitst name should not be null or empty
    @NotEmpty(message = "user fitst name should not be null or empty")
    private String firstName;

    //user last name should not be null or empty
    @NotEmpty(message = "user last name should not be null or empty")
    private String lastName;

    //user email should not be null or empty
    // email address should be valid
    @NotEmpty(message = "user email should not be null or empty")
    @Email(message = "email address should be valid")
    private String email;
}
