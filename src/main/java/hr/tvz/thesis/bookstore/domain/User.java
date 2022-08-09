package hr.tvz.thesis.bookstore.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String username;

  @NotNull
  private String password;

  @NotNull
  private String email;

  @Column(name = "first_name")
  @NotNull
  @Max(100)
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  @Max(100)
  private String lastName;

  @Column(name = "phone_number")
  @NotNull
  @Pattern(regexp = "\\d{9,14}")
  private String phoneNumber;

  @NotNull
  private String address;

  @Column(name = "postal_code")
  @NotNull
  @Size(min = 2, max = 5)
  private String postalCode;

  @NotNull
  private String city;

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  private List<Order> orders;

  @ManyToOne
  @JoinColumn(name = "country_id")
  private Country country;

  @JsonIgnore
  @ManyToMany
  @JoinTable(
      name = "USER_ROLE",
      joinColumns = { @JoinColumn(name = "user_id") },
      inverseJoinColumns = { @JoinColumn(name = "role_id") }
  )
  @BatchSize(size = 20)
  private Set<Role> authorities = new HashSet<>();
}
