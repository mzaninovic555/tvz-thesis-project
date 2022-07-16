package hr.tvz.thesis.bookstore.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountDTO {

  private Long id;

  private BigDecimal discountPrice;

  private LocalDate startedAt;

  private LocalDate endsAt;
}
