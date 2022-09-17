import {Component, OnInit} from '@angular/core';
import {Book} from "../domain/book";
import {BookService} from "../services/book.service";
import {Constants} from "../domain/constants";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements OnInit {
  bookService: BookService;
  books!: Book[];
  booksPopularity!: Book[];
  newBooks!: Book[];
  discountBooks!: Book[];
  imagePath = Constants.IMAGE_PATH;
  isCompleted: boolean = false;
  euroRate: number = Constants.EURO_RATE;

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
          this.books = books;
        },
        error: (err) => {
          console.error(err)
        },
        complete: () => {
          this.sortBooksByDateNewest();
          this.filterBooksByDiscount();
          this.isCompleted = true;
          for (const book of this.books) {
            book.imagePath = this.bookService.bypassImageSecurity(book);
          }
          this.getBooksByPopularity();
        },
      })
  }

  getBooksByPopularity() {
    this.bookService.getBooksByOrderCount()
    .subscribe({
      next: (books) => {
        this.booksPopularity = books;
      },
      complete: () => {
        for (const book of this.booksPopularity) {
          book.imagePath = this.bookService.bypassImageSecurity(book);
        }
      }
    });
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

    for (const book of this.newBooks) {
      book.imagePath = this.bookService.bypassImageSecurity(book);
    }
  }

  filterBooksByDiscount() {
    this.discountBooks = this.books.filter(b => b.discountPrice != 0);
  }
}
