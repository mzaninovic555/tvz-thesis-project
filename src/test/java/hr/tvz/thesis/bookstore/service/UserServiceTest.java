package hr.tvz.thesis.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.domain.dto.UserDTO;
import hr.tvz.thesis.bookstore.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceTest {

  @Autowired
  UserService userService;

  @Autowired
  UserRepository userRepository;

  List<User> mockUsers;

  @BeforeEach
  void setUp() {
    mockUsers = userRepository.findAll();
  }

  @Test
  void findByMockUsername() {
    Optional<UserDTO> mockUser = userService.findByUsername(mockUsers.get(0).getUsername());
    User searched = mockUsers.get(0);

    assertTrue(mockUser.isPresent());
    assertEquals(mockUser.get().getId(), searched.getId());
    assertEquals(mockUser.get().getUsername(), searched.getUsername());
  }

  @Test
  void findByNonExistentUsername() {
    Optional<UserDTO> mockUser = userService.findByUsername("E al fakat ne postoji");

    assertFalse(mockUser.isPresent());
  }

  @Test
  void findAll() {
    List<UserDTO> findAll = userService.findAll();

    assertNotEquals(0, findAll.size());
    assertTrue(
        mockUsers
            .stream()
            .map(User::getUsername)
            .toList()
            .containsAll(findAll.stream().map(UserDTO::getUsername).toList())
    );
  }
}