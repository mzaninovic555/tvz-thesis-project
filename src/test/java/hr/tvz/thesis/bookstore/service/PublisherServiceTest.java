package hr.tvz.thesis.bookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hr.tvz.thesis.bookstore.domain.Publisher;
import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import hr.tvz.thesis.bookstore.repository.PublisherRepository;
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
class PublisherServiceTest {

  @Autowired
  PublisherService publisherService;

  @Autowired
  PublisherRepository publisherRepository;

  List<Publisher> mockPublishers;

  @BeforeEach
  void setUp() {
    mockPublishers = publisherRepository.findAll();
  }

  @Test
  void getAllPublishers() {
    List<PublisherDTO> publishers = publisherService.getAllPublishers();

    assertNotEquals(0, publishers.size());
    assertTrue(
        mockPublishers
            .stream()
            .map(Publisher::getId)
            .toList()
            .containsAll(publishers.stream().map(PublisherDTO::getId).toList())
    );
  }

  @Test
  void findByMockId() {
    Optional<PublisherDTO> found = publisherService.findById(mockPublishers.get(0).getId());
    Publisher mock = mockPublishers.get(0);

    assertTrue(found.isPresent());
    assertEquals(found.get().getId(), mock.getId());
    assertEquals(found.get().getName(), mock.getName());
  }

  @Test
  void findByNonExistentId() {
    Optional<PublisherDTO> found = publisherService.findById(-123L);

    assertFalse(found.isPresent());
  }

  @Test
  void saveTest() {
    Publisher mockPublisher = new Publisher(0L, "Testni", null);
    PublisherDTO mockPublisherDTO = publisherService.save(mockPublisher);
    Optional<Publisher> mockPublisherOptional = publisherRepository.findById(mockPublisherDTO.getId());

    assertTrue(mockPublisherOptional.isPresent());
    assertEquals(mockPublisherOptional.get().getId(), mockPublisherDTO.getId());
    assertEquals(mockPublisherOptional.get().getName(), mockPublisherDTO.getName());
  }
}