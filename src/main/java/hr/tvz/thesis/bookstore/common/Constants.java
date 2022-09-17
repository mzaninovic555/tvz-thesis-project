package hr.tvz.thesis.bookstore.common;

import java.math.BigDecimal;
import java.util.List;

public class Constants {

  public static final List<String> AUTHENTICATED_ENDPOINTS = List.of(
      "/api/orders/add",
      "/api/orders/user/",
      "/api/user/",
      "/orders/",
      "/api/add/"
  );

  public static final String IMAGE_PATH = "C:\\Users\\mzani\\Desktop\\Thesis\\books\\";

  public static final BigDecimal EURO_RATE = new BigDecimal("7.53450");
}
