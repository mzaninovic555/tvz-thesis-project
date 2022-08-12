package hr.tvz.thesis.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hr.tvz.thesis.bookstore.domain.Author;
import hr.tvz.thesis.bookstore.domain.dto.AuthorDTO;
import hr.tvz.thesis.bookstore.repository.AuthorRepository;
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
class AuthorServiceTest {

  @Autowired
  AuthorService authorService;

  @Autowired
  AuthorRepository authorRepository;

  private static List<Author> mockAuthors;

  @BeforeEach
  void setUp() {
    mockAuthors = authorRepository.findAll();
  }

  @Test
  void getAllAuthorsTest() {
    List<AuthorDTO> allAuthors = authorService.getAllAuthors();

    assertNotEquals(0, allAuthors.size());
    assertTrue(
        allAuthors
            .stream()
            .map(AuthorDTO::getId)
            .toList()
            .containsAll(mockAuthors.stream().map(Author::getId).toList())
    );
  }

  @Test
  void getAuthorByFirstIdTest() {
    Optional<AuthorDTO> foundByFirstId = authorService.getAuthorById(mockAuthors.get(0).getId());

    assertTrue(foundByFirstId.isPresent());
    assertEquals(foundByFirstId.get().getFirstName(), mockAuthors.get(0).getFirstName());
    assertEquals(foundByFirstId.get().getLastName(), mockAuthors.get(0).getLastName());
  }

  @Test
  void getAuthorByNonExistendIdTest() {
    Optional<AuthorDTO> foundByFirstId = authorService.getAuthorById(-120L);
    assertFalse(foundByFirstId.isPresent());
  }

  @Test
  void saveTest() {
    Author mockAuthor = new Author(0L, "Testni", "Tester", null);
    AuthorDTO mockAuthorDTO = authorService.save(mockAuthor);
    Optional<Author> optionalAuthorDTO = authorRepository.findById(mockAuthorDTO.getId());

    assertTrue(optionalAuthorDTO.isPresent());
    assertEquals(optionalAuthorDTO.get().getId(), mockAuthorDTO.getId());
    assertEquals(optionalAuthorDTO.get().getFirstName(), mockAuthorDTO.getFirstName());
    assertEquals(optionalAuthorDTO.get().getLastName(), mockAuthorDTO.getLastName());
  }
}