package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.common.DTOConverters;
import hr.tvz.thesis.bookstore.domain.Publisher;
import hr.tvz.thesis.bookstore.domain.dto.PublisherDTO;
import hr.tvz.thesis.bookstore.repository.PublisherRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl implements PublisherService {

  private final PublisherRepository publisherRepository;

  public PublisherServiceImpl(PublisherRepository publisherRepository) {
    this.publisherRepository = publisherRepository;
  }

  @Override
  public List<PublisherDTO> getAllPublishers() {
    return publisherRepository.findAll()
        .stream()
        .map(DTOConverters::mapPublisherToPublisherDTO)
        .toList();
  }

  @Override
  public Optional<PublisherDTO> findById(Long id) {
    return publisherRepository.findById(id).map(DTOConverters::mapPublisherToPublisherDTO);
  }

  @Override
  public PublisherDTO save(Publisher publisher) {
    return DTOConverters.mapPublisherToPublisherDTO(publisherRepository.save(publisher));
  }
}
