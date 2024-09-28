package az.company.onlinelibrarysystem.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity; enable for production
                .authorizeHttpRequests(auth -> auth
                                .anyRequest()
                                .permitAll()

//                        .requestMatchers("/api/auth/**").permitAll()  // Allow register and login endpoints
//                        .requestMatchers("/api/**").hasAuthority("ROLE_SUPERADMIN")  // Only ADMIN can access admin endpoints
//                        .requestMatchers("/api/books/**",
//                                "api/reservations/**",
//                                "api/notifications/**",
//                                "api/reports/**",
//                                "api/books/**",
//                                "api/authors/**").hasRole("ADMIN")
//                        .requestMatchers("api/books/search",
//                                "api/books/filter",
//                                "api/books/author",
//                                "api/books/count",
//                                "api/notifications/**",
//                                "api/users/**").hasRole("USER")
//                        // Allow both USER and ADMIN roles to access user endpoints
//                        .anyRequest().authenticated()  // Require authentication for all other requests
//                )
//                .formLogin().disable()  // Disable form login
//                .logout(logout -> logout
//                        .logoutUrl("/api/auth/logout")
//                        .logoutSuccessUrl("/api/auth/login?logout")
//                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
