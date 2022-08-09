package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Publisher;
import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import java.util.List;
import java.util.Optional;

public interface PublisherService {

  List<PublisherDTO> getAllPublishers();

  Optional<PublisherDTO> findById(Long id);

  PublisherDTO save(Publisher publisher);
}
