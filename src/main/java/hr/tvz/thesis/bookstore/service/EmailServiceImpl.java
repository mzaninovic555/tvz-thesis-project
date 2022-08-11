package hr.tvz.thesis.bookstore.service;

import hr.tvz.thesis.bookstore.domain.Book;
import hr.tvz.thesis.bookstore.domain.Order;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.data.util.Pair;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine springTemplateEngine;

  public EmailServiceImpl(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine) {
    this.javaMailSender = javaMailSender;
    this.springTemplateEngine = springTemplateEngine;
  }

  @Override
  public void sendNewOrderEmail(String to, String subject, Order order, List<Pair<Book, Integer>> bookIdQuantity)
      throws MessagingException {
    MimeMessage message = javaMailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

    helper.setFrom("tvzbookstoremail@gmail.com");
    helper.setTo(to);
    helper.setSubject(subject);

    Context context = new Context();
    context.setVariable("user", order.getUser().getFirstName() + " " + order.getUser().getLastName());
    context.setVariable("order", order);
    context.setVariable("bookIdQuantity", bookIdQuantity);

    String html = springTemplateEngine.process("/new-order.html", context);
    helper.setText(html, true);
    javaMailSender.send(message);

  }
}
