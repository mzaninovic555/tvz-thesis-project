package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import java.util.Optional;

public interface PublisherService {

  public Optional<PublisherDTO> findById(Long id);
}
