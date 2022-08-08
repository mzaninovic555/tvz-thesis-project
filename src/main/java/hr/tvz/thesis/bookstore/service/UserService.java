package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import java.util.Optional;

public interface UserService {

  Optional<UserDTO> findByUsername(String username);
}
