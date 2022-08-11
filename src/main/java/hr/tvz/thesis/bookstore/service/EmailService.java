package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Order;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.data.util.Pair;

public interface EmailService {

  void sendNewOrderEmail(String to, String subject, Order order, List<Pair<Book, Integer>> bookIdQuantity)
      throws MessagingException;
}
