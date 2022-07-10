package hr.tvz.thesis.bookstore.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
}
