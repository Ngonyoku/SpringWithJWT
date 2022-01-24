package ke.co.ngonyoku.SpringWithJWT.repo;

import ke.co.ngonyoku.SpringWithJWT.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
