package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.User;

public interface JwtService {

  Boolean authenticate(String jwtToken);

  String createJwtToken(User user);
}
