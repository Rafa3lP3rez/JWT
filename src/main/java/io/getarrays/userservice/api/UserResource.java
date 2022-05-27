package io.getarrays.userservice.api;


import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.domain.User;
import io.getarrays.userservice.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api") @RequiredArgsConstructor
public class UserResource {

    private final UserService userService;

    @GetMapping("/users")
    // para que sea una entidad de respuesta
    public ResponseEntity<List<User>> getUsers(){

        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/user/save")
    // para que sea una entidad de respuesta
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    // para que sea una entidad de respuesta
    public ResponseEntity<Role> roleUser(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    // para que sea una entidad de respuesta
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userService.addRoleToUser(form.getUsername(), form.getRolName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/role/addtouser")
    // para que sea una entidad de respuesta
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        String authorizationHeader = request.getHeader(AUTHORIZATION);
    }
}

@Data
class RoleToUserForm{

    private String username;
    private String rolName;
}