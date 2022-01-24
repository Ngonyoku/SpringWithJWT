package ke.co.ngonyoku.SpringWithJWT.service;

import ke.co.ngonyoku.SpringWithJWT.domain.Role;
import ke.co.ngonyoku.SpringWithJWT.domain.User;
import ke.co.ngonyoku.SpringWithJWT.repo.RoleRepo;
import ke.co.ngonyoku.SpringWithJWT.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional  //Everytime a method is called, Spring will automatically save changes
@Slf4j  //For logs
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            log.error("User NOT found in the database");
            throw new UsernameNotFoundException("User NOT found in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user
                .getRoles()
                .forEach(
                        role -> {
                            authorities.add(new SimpleGrantedAuthority(role.getName()));
                        })
        ;

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new User {} to database", user.getName());
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new Role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);

        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);

        user.getRoles().add(role);
        log.info("Saving new User to database");
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {} from database", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users from the database");
        return userRepo.findAll();
    }
}
