import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../services/book.service";
import {Book} from "../domain/book";
import {Constants} from "../domain/constants";

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.css']
})
export class BookSearchComponent implements OnInit {

  books!: Book[];
  filteredBooks!: Book[];
  searchTerm!: string;
  imagePath = Constants.IMAGE_PATH;
  filteredBookTitle = "";
  filteredBookAuthor = "";
  filteredBookPublisher = "";
  filteredBookCategory = "";

  constructor(private route: ActivatedRoute, private bookService: BookService) { }

  ngOnInit(): void {
    this.searchTerm = this.route.snapshot.paramMap.get("searchTerm") || "";

    if (this.searchTerm != null) {
      this.bookService.getBooksByTitle(this.searchTerm)
        .subscribe({
          next: (books) => {
            this.books = books;
          },
          error: () => {
            console.log("Error")
          },
          complete: () => {
            this.filteredBooks = this.books;
          }
        });
    } else {
      console.error(`ID can't be null.`);
    }
  }

  searchBookTitle(data: string) {
    this.filteredBookTitle = data;
    this.filterBooks();
  }

  searchBookAuthor(data: string) {
    this.filteredBookAuthor = data;
    this.filterBooks();
  }

  searchBookPublisher(data: string) {
    this.filteredBookPublisher = data;
    this.filterBooks();
  }

  searchBookCategory(data: string) {
    this.filteredBookCategory = data;
    this.filterBooks();
  }

  filterBooks() {
    this.filteredBooks = this.books;

    if (this.filteredBookTitle !== "") {
      this.filteredBooks = this.filteredBooks
        .filter(b => b.title.toLowerCase().includes(this.filteredBookTitle.toLowerCase()));
    }

    if (this.filteredBookAuthor !== "") {
      this.filteredBooks = this.filteredBooks
        .filter(b => (`${b.author.firstName} ${b.author.lastName}`).toLowerCase()
          .includes(this.filteredBookAuthor.toLowerCase()));
    }

    if (this.filteredBookPublisher !== "") {
      this.filteredBooks = this.filteredBooks
        .filter(b => b.publisher.name.toLowerCase().includes(this.filteredBookPublisher.toLowerCase()));
    }

    if (this.filteredBookCategory !== "") {
      this.filteredBooks = this.filteredBooks
        .filter(b => b.categories.some(c => {
          return c.name.toLowerCase().includes(this.filteredBookCategory.toLowerCase());
        }));
    }

    if (this.filteredBookTitle === ""
        && this.filteredBookAuthor === ""
        && this.filteredBookPublisher === ""
        && this.filteredBookCategory === "") {
      this.filteredBooks = this.books;
    }
  }
}
