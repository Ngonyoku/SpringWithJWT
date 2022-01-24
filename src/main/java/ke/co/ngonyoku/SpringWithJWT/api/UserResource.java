package ke.co.ngonyoku.SpringWithJWT.api;

import ke.co.ngonyoku.SpringWithJWT.domain.Role;
import ke.co.ngonyoku.SpringWithJWT.domain.User;
import ke.co.ngonyoku.SpringWithJWT.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity
                .ok()  //Everything went OK
                .body(userService.getUsers());  //Return the list of user in the body
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUsers(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity
                .created(uri)
                .body(userService.saveUser(user));  //Return the list of user in the body
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
        return ResponseEntity
                .created(uri)
                .body(userService.saveRole(role));  //Return the list of user in the body
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form) {
        userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity
                .ok()
                .build();  //Return the list of user in the body
    }
}

@Data
class RoleToUserForm {
    private String username;
    private String roleName;
}
