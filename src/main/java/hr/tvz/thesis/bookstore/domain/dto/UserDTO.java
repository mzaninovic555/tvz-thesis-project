package hr.tvz.thesis.bookstore.domain.dto;

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
public class UserDTO {

  private Long id;

  private String username;

  private String email;

  private String firstName;

  private String lastName;

  private String phoneNumber;

  private String address;

  private String postalCode;

  private String city;
}
