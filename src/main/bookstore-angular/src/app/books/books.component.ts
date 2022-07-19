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
  imagePath = Constants.IMAGE_PATH;

  constructor(bookService: BookService) {
    this.bookService = bookService;
    this.getBooks();
  }

  ngOnInit(): void {
  }

  getBooks(): void {
    this.bookService.getBooks()
      .subscribe(
        books => {
          this.books = books;
        });
  }
}
