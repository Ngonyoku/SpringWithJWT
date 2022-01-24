package ke.co.ngonyoku.SpringWithJWT.repo;

import ke.co.ngonyoku.SpringWithJWT.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
