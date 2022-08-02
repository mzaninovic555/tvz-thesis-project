package hr.tvz.thesis.bookstore.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
  @GeneratedValue
  private Long id;

  private String name;

  @Column(name = "country_code")
  private String countryCode;

  @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
  private List<User> users;
}
