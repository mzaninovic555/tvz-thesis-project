package hr.tvz.thesis.bookstore.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Discount {

  @Id
  private Long id;

  @Column(name = "discount_price")
  private BigDecimal discountPrice;

  @Column(name = "started_at")
  private LocalDate startedAt;

  @Column(name = "ends_at")
  private LocalDate endsAt;

  @OneToOne
  @JoinColumn(name = "book_id")
  private Book book;
}
