package hr.tvz.thesis.bookstore.domain.dto;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

  @Id
  private Long id;

  private String username;

  private String password;

  private String email;

  private String firstName;

  private String lastName;

  private String phoneNumber;

  private String address;

  private String postalCode;

  private String city;
}