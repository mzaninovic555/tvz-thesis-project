package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.Login;
import hr.tvz.thesis.bookstore.domain.Role;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.LoginDTO;
import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import hr.tvz.thesis.bookstore.repository.RoleRepository;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

  private final JwtService jwtService;
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final EmailService emailService;

  public LoginServiceImpl(JwtService jwtService, UserRepository userRepository,
      RoleRepository roleRepository, EmailService emailService) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.emailService = emailService;
  }

  @Override
  public Optional<LoginDTO> login(Login login) {
    Optional<User> user = userRepository.findByUsername(login.getUsername());

    if (user.isEmpty() || !isMatchingPassword(login.getPassword(), user.get().getPassword())) {
      return Optional.empty();
    }

    return Optional.of(new LoginDTO(jwtService.createJwtToken(user.get())));
  }

  @Override
  public UserDTO register(User user) throws MessagingException {
    List<User> allUsers = userRepository.findAll();

    if (allUsers.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getUsername()))
          || allUsers.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()))) {
      return null;
    }

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String cryptedPassword = passwordEncoder.encode(user.getPassword());
    user.setPassword("temporaryPassword");
    Role userRole = roleRepository.findByName("ROLE_USER").get();
    user.getAuthorities().add(userRole);

    emailService.sendRegistrationEmail(user.getEmail(), "Potvrda o registraciji", user);

    UserDTO savedUser =  DTOConverters.mapUserToUserDTO(userRepository.save(user));
    userRepository.updatePassword(savedUser.getId(), cryptedPassword);

    return savedUser;
  }

  private boolean isMatchingPassword(String password, String userPassword) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.matches(password, userPassword);
  }
}
