package hr.tvz.thesis.bookstore.domain.dto;

import hr.tvz.thesis.bookstore.domain.Book;
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
public class ReviewDTO {

  private Long id;

  private Integer score;

  private Long userId;
}
