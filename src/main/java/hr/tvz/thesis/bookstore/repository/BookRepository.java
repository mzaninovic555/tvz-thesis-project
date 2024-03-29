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

  @Query(value =
      """
        select b.id, format, page_number, binding, mass, barcode, title, price, description, publishing_year, stock, isbn, image_path, date_added, publisher_id, language_id, author_id
        from book b
        join orders_book ob on b.id = ob.book_id
        group by b.id
        order by (select count(*) from book b2 join orders_book ob2 on b2.id = ob2.book_id join orders o2 on o2.id = ob2.order_id where b2.id = b.id) desc;
      """, nativeQuery = true)
  List<Book> findBooksByOrderCount();

  List<Book> findByAuthorId(Long authorId);

  List<Book> findByPublisherId(Long publisherId);

  List<Book> findByTitleContainsIgnoreCase(String title);

  List<Book> findByCategoriesId(Long categoryId);

  List<Book> findByOrdersId(Long ordersId);

  @Modifying
  @Query(value =
      """
        update book
        set image_path = :imagePath
        where id = :bookId
      """, nativeQuery = true)
  int saveBookImage(Long bookId, String imagePath);

  @Modifying
  @Query(value =
    """
      update book
      set stock = stock - :quantityToReduce
      where id = :bookId
    """, nativeQuery = true)
  int updateStock(Long bookId, Integer quantityToReduce);
}
