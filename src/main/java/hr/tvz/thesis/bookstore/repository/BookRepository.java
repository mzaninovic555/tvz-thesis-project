package hr.tvz.thesis.bookstore.repository;

import hr.tvz.thesis.bookstore.domain.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findByAuthorId(Long authorId);

  List<Book> findByPublisherId(Long publisherId);

  List<Book> findByTitleContainsIgnoreCase(String title);

  List<Book> findByCategoriesId(Long categoryId);

  @Modifying
  @Query(value =
    """
      update book
      set stock = stock - :quantityToReduce
      where id = :bookId
    """, nativeQuery = true)
  int updateStock(Long bookId, Integer quantityToReduce);
}
