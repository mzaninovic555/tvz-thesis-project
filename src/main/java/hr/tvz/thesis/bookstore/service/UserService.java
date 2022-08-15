package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;

public interface UserService {

  Optional<UserDTO> findByUsername(String username);

  List<UserDTO> findAll();

  Integer changePassword(String newPassword, Long userId) throws MessagingException;
}
