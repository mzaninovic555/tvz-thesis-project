package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Login;
import hr.tvz.thesis.bookstore.domain.dto.LoginDTO;
import java.util.Optional;

public interface LoginService {

  Optional<LoginDTO> login(Login login);
}
