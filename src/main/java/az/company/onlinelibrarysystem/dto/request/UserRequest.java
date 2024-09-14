package az.company.onlinelibrarysystem.dto.request;

import az.company.onlinelibrarysystem.entity.Role;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
