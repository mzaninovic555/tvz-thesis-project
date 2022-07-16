package hr.tvz.thesis.bookstore.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorDTO {

  private Long id;

  private String firstName;

  private String lastName;
}
