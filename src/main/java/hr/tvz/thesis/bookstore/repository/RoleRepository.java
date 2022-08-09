package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

  Optional<Role> findByName(String name);
}
