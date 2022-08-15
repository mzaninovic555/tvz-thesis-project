package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import javax.mail.MessagingException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final EmailService emailService;

  public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
    this.userRepository = userRepository;
    this.emailService = emailService;
  }

  @Override
  public Optional<UserDTO> findByUsername(String username) {
    return userRepository.findByUsername(username).map(DTOConverters::mapUserToUserDTO);
  }

  @Override
  public List<UserDTO> findAll() {
    return userRepository.findAll()
        .stream()
        .map(DTOConverters::mapUserToUserDTO)
        .toList();
  }

  @Override
  public Integer changePassword(String newPassword, Long userId) throws MessagingException {
    if (newPassword.length() < 7 || newPassword.length() > 32) {
      return 0;
    }

    User user = userRepository.findById(userId).get();
    emailService.sendChangePasswordEmail(user.getEmail(), "Promjena lozinke", user);

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String newCryptedPassword = passwordEncoder.encode(newPassword);
    return userRepository.updatePassword(userId, newCryptedPassword);
  }
}
