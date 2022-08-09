package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

}
