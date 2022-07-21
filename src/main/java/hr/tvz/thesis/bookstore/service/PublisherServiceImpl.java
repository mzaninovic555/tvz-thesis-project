package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import hr.tvz.thesis.bookstore.repository.PublisherRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

  private final PublisherRepository publisherRepository;

  public PublisherServiceImpl(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  @Override
  public Optional<PublisherDTO> findById(Long id) {
    return publisherRepository.findById(id)
        .map(DTOConverters::mapPublisherToPublisherDTO);
  }
}
