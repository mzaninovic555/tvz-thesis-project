package hr.tvz.thesis.bookstore.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDTO {

  private Long id;

  private LocalDateTime datePlaced;

  private BigDecimal totalPrice;

  private UserDTO user;

  private List<BookDTO> books;
}
