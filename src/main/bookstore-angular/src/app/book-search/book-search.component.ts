import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../book.service";
import {Book} from "../book";
import {Constants} from "../constants";

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.css']
})
export class BookSearchComponent implements OnInit {

  books!: Book[];
  filteredBooks!: Book[];
  imagePath = Constants.IMAGE_PATH;
  filteredBookTitle = "";
  filteredBookAuthor = "";
  filteredBookPublisher = "";

  constructor(private route: ActivatedRoute, private bookService: BookService) { }

  ngOnInit(): void {
    const searchTerm = this.route.snapshot.paramMap.get("searchTerm") || "";
    this.bookService.getBooksByTitle(searchTerm)
      .subscribe();

    if (searchTerm != null) {
      this.bookService.getBooksByTitle(searchTerm)
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

    if (this.filteredBookTitle === "" && this.filteredBookAuthor === "" && this.filteredBookPublisher === "") {
      this.filteredBooks = this.books;
    }
  }
}
