package io.getarrays.userservice;

import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class UserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserserviceApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {

            userService.saveRole(new Role(null, "ROLE_USER"));
            userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_ADMIN"));
            userService.saveRole(new Role(null, "ROLE_MANAGER"));

            userService.saveUser(new User(null, "Rafael Perez", "rafita", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Ashley Rodriguez", "asnito", "1234", new ArrayList<>()));
            userService.saveUser(new User(null, "Jose Antonio", "juarez", "1234", new ArrayList<>()));

            userService.addRoleToUser("rafita", "ROLE_ADMIN");
            userService.addRoleToUser("rafita", "ROLE_MANAGER");
            userService.addRoleToUser("rafita", "ROLE_USER");
            userService.addRoleToUser("asnito", "ROLE_MANAGER");
            userService.addRoleToUser("juarez", "ROLE_USER");


        };
    }


}
