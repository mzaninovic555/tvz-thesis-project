import {Component, OnInit} from '@angular/core';
import {BookService} from "../book.service";
import {Author} from "../author";
import {Constants} from "../constants";
import {Book} from "../book";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  author?: Author;
  books!: Book[];
  filteredBooks!: Book[];
  imagePath = Constants.IMAGE_PATH;
  filteredBookTitle = "";
  filteredBookPublisher = "";

  constructor(private route: ActivatedRoute, private bookService: BookService) {
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id != null) {
      this.bookService.getAuthorById(id)
      .subscribe(author => {
        this.author = author;
      });

      this.bookService.getBooksByAuthorId(id)
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

    if (this.filteredBookPublisher !== "") {
      this.filteredBooks = this.filteredBooks
      .filter(b => b.publisher.name.toLowerCase().includes(this.filteredBookPublisher.toLowerCase()));
    }

    if (this.filteredBookTitle === "" && this.filteredBookPublisher === "") {
      this.filteredBooks = this.books;
    }
  }
}
