package hr.tvz.thesis.bookstore.config;

import hr.tvz.thesis.bookstore.common.Constants;
import hr.tvz.thesis.bookstore.service.JwtService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Log4j2
public class JwtFilter extends OncePerRequestFilter {

  public static final String AUTHORIZATION_HEADER = "Authorization";
  public static final String AUTHORIZATION_TOKEN_PREFIX = "Bearer ";

  private final JwtService jwtService;

  public JwtFilter(JwtService jwtService) {
    this.jwtService = jwtService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    request.setCharacterEncoding(StandardCharsets.UTF_8.name());
    response.setCharacterEncoding(StandardCharsets.UTF_8.name());

    if (isEndpointAuthenticated(request)) {
      String jwtToken = extractJwt(request);
      log.trace("Filtering for endpoint: {}, resolved JWT: {}", request.getRequestURI(), jwtToken);

      if (jwtToken != null && !jwtToken.isEmpty()) {
        Boolean authenticate = jwtService.authenticate(jwtToken);

        if (!authenticate) {
          unauthorized(response);
        }
      } else {
        unauthorized(response);
      }
    }

    filterChain.doFilter(request, response);
  }

  private void unauthorized(HttpServletResponse response) {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }

  private String extractJwt(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION_HEADER);
    if (token != null && token.startsWith(AUTHORIZATION_TOKEN_PREFIX)) {
      return token.substring(AUTHORIZATION_TOKEN_PREFIX.length());
    }
    return null;
  }

  private Boolean isEndpointAuthenticated(HttpServletRequest request) {
    String uri = request.getRequestURI();
    return Constants.AUTHENTICATED_ENDPOINTS.stream().anyMatch(uri::contains);
  }
}
