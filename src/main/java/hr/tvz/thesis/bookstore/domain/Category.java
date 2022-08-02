package hr.tvz.thesis.bookstore.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany(targetEntity = Book.class)
  @JoinTable(
      name = "book_category",
      joinColumns = { @JoinColumn(name = "category_id") },
      inverseJoinColumns = { @JoinColumn(name = "book_id") }
  )
  private List<Book> books;
}
