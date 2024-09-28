package az.company.onlinelibrarysystem.dto.request;

import az.company.onlinelibrarysystem.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserRequest {
    @NotNull(message = "username can not be null")
    @NotEmpty(message = "username can not be empty")
    private String username;
    @NotNull(message = "password can not be null")
    @NotEmpty(message = "password can not be empty")
    private String password;
    @NotNull(message = "email can not be null")
    @NotEmpty(message = "email can not be empty")
    @Email(message = "Email format not valid")
    private String email;
    @NotNull(message = "role can not be null")
    @NotEmpty(message = "role can not be empty")
    private Role role;
}
