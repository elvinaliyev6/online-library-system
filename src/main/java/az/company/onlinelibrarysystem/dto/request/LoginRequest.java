package az.company.onlinelibrarysystem.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
