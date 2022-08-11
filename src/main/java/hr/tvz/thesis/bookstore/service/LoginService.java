package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Login;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.LoginDTO;
import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import java.util.Optional;
import javax.mail.MessagingException;

public interface LoginService {

  Optional<LoginDTO> login(Login login);

  UserDTO register(User user) throws MessagingException;
}
