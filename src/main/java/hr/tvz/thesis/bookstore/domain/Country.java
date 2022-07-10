package hr.tvz.thesis.bookstore.domain;

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
public class Country {

  @Id
  private Long id;

  private String name;

  @Column(name = "country_code")
  private String countryCode;
}
