package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Role;
import hr.tvz.thesis.bookstore.domain.User;
import hr.tvz.thesis.bookstore.user.ApplicationUser;
import hr.tvz.thesis.bookstore.user.UserAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class JwtServiceImpl implements JwtService {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.validity-seconds}")
  private Long jwtValiditySeconds;


  @Override
  public Boolean authenticate(String jwtToken) {
    if (isJwtValid(jwtToken)) {
      ApplicationUser applicationUser = getApplicationUserFromJwt(jwtToken);
      saveAuthentication(applicationUser);
      return true;
    }
    return false;
  }

  @Override
  public String createJwtToken(User user) {
    Instant expiration = Instant.now().plusSeconds(jwtValiditySeconds);
    String authorities = user.getAuthorities()
        .stream()
        .map(Role::getName)
        .collect(Collectors.joining(","));

    return Jwts
        .builder()
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .setSubject(user.getUsername())
        .setExpiration(new Date(expiration.toEpochMilli()))
        .setIssuedAt(new Date())
        .claim("authorities", authorities)
        .compact();
  }

  private Boolean isJwtValid(String jwtToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(jwtToken);
      return true;
    } catch (SignatureException e) {
      log.debug("Invalid JWT signature.");
      log.trace("Invalid JWT signature trace: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      log.debug("Invalid JWT token.");
      log.trace("Invalid JWT token trace: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.debug("Expired JWT token.");
      log.trace("Expired JWT token trace: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.debug("Unsupported JWT token.");
      log.trace("Unsupported JWT token trace: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.debug("JWT token compact of handler are invalid.");
      log.trace("JWT token compact of handler are invalid trace: {}", e.getMessage());
    }
    return true;
  }

  private ApplicationUser getApplicationUserFromJwt(String jwtToken) {
    Claims claims = Jwts
        .parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(jwtToken)
        .getBody();

    List<SimpleGrantedAuthority> authorities = Arrays
        .stream(claims.get("authorities").toString().split(","))
        .map(SimpleGrantedAuthority::new)
        .toList();

    ApplicationUser applicationUser = new ApplicationUser();
    applicationUser.setUsername(claims.getSubject());
    applicationUser.setAuthorities(authorities);

    return applicationUser;
  }

  private void saveAuthentication(ApplicationUser applicationUser) {
    Authentication authentication = new UserAuthentication(applicationUser);
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }
}
