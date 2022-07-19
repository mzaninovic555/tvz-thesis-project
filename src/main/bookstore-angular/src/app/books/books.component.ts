import {Component, OnInit} from '@angular/core';
import {Book} from "../book";
import {BookService} from "../book.service";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  bookService: BookService;
  books!: Book[];

  constructor(bookService: BookService) {
    this.bookService = bookService;
    this.getBooks();
    console.log("Book service initialized.");
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
