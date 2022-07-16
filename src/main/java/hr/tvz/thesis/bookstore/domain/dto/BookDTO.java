package hr.tvz.thesis.bookstore.domain.dto;

import java.math.BigDecimal;
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
public class BookDTO {

  private Long id;

  private String format;

  private Integer pageNumber;

  private String binding;

  private Double mass;

  private String barcode;

  private String title;

  private BigDecimal price;

  private String description;

  private Integer publishingYear;

  private Integer stock;

  private Boolean isDiscount;

  private String isbn;

  private String imagePath;

}
