package hr.tvz.thesis.bookstore.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order {

  @Id
  private Long id;

  @Column(name = "date_placed")
  private LocalDateTime datePlaced;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany(targetEntity = Book.class)
  @JoinTable(
      name = "order_book",
      joinColumns = { @JoinColumn(name = "order_id") },
      inverseJoinColumns = { @JoinColumn(name = "book_id") }
  )
  private List<Book> books;
}
