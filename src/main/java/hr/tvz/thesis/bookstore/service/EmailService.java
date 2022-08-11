package hr.tvz.thesis.bookstore.service;

public interface EmailService {

  void sendNewOrderEmail(String to, String subject, String text);
}
