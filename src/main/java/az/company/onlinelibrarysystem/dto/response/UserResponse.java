package az.company.onlinelibrarysystem.dto.response;

import az.company.onlinelibrarysystem.entity.Role;
import lombok.Data;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
}
