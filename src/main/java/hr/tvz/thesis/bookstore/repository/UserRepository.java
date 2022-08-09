package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  @Modifying
  @Query(value =
      """
        update user
        set password = :newPassword
        where id = :userId
      """, nativeQuery = true)
  int updatePassword(Long userId, String newPassword);
}
