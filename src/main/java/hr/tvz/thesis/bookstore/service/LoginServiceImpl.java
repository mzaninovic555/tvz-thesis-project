package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Login;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.LoginDTO;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  public LoginServiceImpl(JwtService jwtService, UserRepository userRepository) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
  }

  @Override
  public Optional<LoginDTO> login(Login login) {

    Optional<User> user = userRepository.findByUsername(login.getUsername());

    if (user.isEmpty() || !isMatchingPassword(login.getPassword(), user.get().getPassword())) {
      return Optional.empty();
    }

    return Optional.of(new LoginDTO(jwtService.createJwtToken(user.get())));
  }

  private boolean isMatchingPassword(String password, String userPassword) {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(password, userPassword);
  }
}
