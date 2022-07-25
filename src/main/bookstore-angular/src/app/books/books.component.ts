import {Component, OnInit} from '@angular/core';
import {Book} from "../book";
import {BookService} from "../book.service";
import {Constants} from "../constants";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  bookService: BookService;
  books!: Book[];
  newBooks!: Book[];
  discountBooks!: Book[];
  imagePath = Constants.IMAGE_PATH;

  constructor(bookService: BookService) {
    this.bookService = bookService;
    this.getBooks();
  }

  ngOnInit(): void {
  }

  getBooks(): void {
    this.bookService.getBooks()
      .subscribe({
        next: (books) => {
          this.books = books
        },
        error: (err) => {
          console.error(err)
        },
        complete: () => {
          this.sortBooksByDateNewest();
          this.filterBooksByDiscount();
        },
      })
  }

  sortBooksByDateNewest() {
    this.newBooks = this.books.map(b => Object.assign({}, b));
    this.newBooks.sort((a, b)=> {
      if (a.dateAdded < b.dateAdded) {
        return 1
      }
      if (a.dateAdded > b.dateAdded) {
        return -1;
      }
      return 0;
    });
  }

  filterBooksByDiscount() {
    this.discountBooks = this.books.filter(b => b.discountPrice != 0);
  }
}
