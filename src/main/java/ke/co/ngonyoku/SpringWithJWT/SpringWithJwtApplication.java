package ke.co.ngonyoku.SpringWithJWT;

import ke.co.ngonyoku.SpringWithJWT.domain.Role;
import ke.co.ngonyoku.SpringWithJWT.domain.User;
import ke.co.ngonyoku.SpringWithJWT.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringWithJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWithJwtApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(UserService service) {
        return args -> {
            service.saveRole(new Role(null, "ROLE_USER"));
            service.saveRole(new Role(null, "ROLE_MANAGER"));
            service.saveRole(new Role(null, "ROLE_ADMIN"));
            service.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

            service.saveUser(new User(null, "Mary Gakame", "mary", "123456789", new ArrayList<>()));
            service.saveUser(new User(null, "Cynthia", "Mutai", "123456789", new ArrayList<>()));
            service.saveUser(new User(null, "Jeremy", "Matiba", "123456789", new ArrayList<>()));
            service.saveUser(new User(null, "Lyn", "Waithera", "123456789", new ArrayList<>()));
            service.saveUser(new User(null, "Zion", "Omondi", "123456789", new ArrayList<>()));

            service.addRoleToUser("mutai", "ROLE_MANAGER");
            service.addRoleToUser("Omondi", "ROLE_ADMIN");
            service.addRoleToUser("Matiba", "ROLE_USER");
            service.addRoleToUser("Waithera", "ROLE_MANAGER");
            service.addRoleToUser("Waithera", "ROLE_ADMIN");
        };
    }
}
