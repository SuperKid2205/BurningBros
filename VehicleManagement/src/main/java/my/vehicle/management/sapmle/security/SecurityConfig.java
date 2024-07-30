package my.vehicle.management.sapmle.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        
        // define query to retrieve a user by username
        manager.setUsersByUsernameQuery(
                "SELECT USER_NAME, PASSWORD, ENABLED FROM MST_USER WHERE USER_NAME = ?");
        
        // define query to retrieve a the roles by username
        manager.setAuthoritiesByUsernameQuery(
                "SELECT USER_NAME, ROLES FROM MST_USER WHERE USER_NAME = ?");
        
        return manager;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    
    @Bean
    public AuthenticationManager authenticationManager(
                                 AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//
//        httpSecurity.authorizeHttpRequests(
//                (auth) -> {
//                    auth.requestMatchers("/api/users","/api/login").permitAll();
////                  auth.requestMatchers("/api/admin/**").hasRole("ADMIN");
////                  auth.requestMatchers("/api/user/**").hasAnyRole("ADMIN","USER");
//                  auth.requestMatchers("/api/admin/**").permitAll();
//                  auth.requestMatchers("/api/user/**").permitAll();
//                    auth.anyRequest().authenticated();
//                    
//                })
//                .csrf(csrf -> {
//                    csrf.ignoringRequestMatchers("/api/**");
//                })
//        ;
//
//        return httpSecurity.build();
//    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(theConfig -> theConfig
                .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/vehicles").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST, "/api/vehicles").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/vehicles").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.GET, "/api/maintenances").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.POST, "/api/maintenances").hasAnyRole("ADMIN","USER")
                .requestMatchers(HttpMethod.PUT, "/api/maintenances").hasAnyRole("ADMIN","USER")
                .requestMatchers("/api/vehicles").permitAll()
        );
        
        // use HTTP Basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());
        
        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE and/or PATCH
        httpSecurity.csrf(theCsrd -> theCsrd.disable());

        return httpSecurity.build();
    }
}
