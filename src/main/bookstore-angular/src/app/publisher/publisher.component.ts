import {Component, OnInit} from '@angular/core';
import {Book} from "../domain/book";
import {Constants} from "../domain/constants";
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../services/book.service";
import {Publisher} from "../domain/publisher";

@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.component.html',
  styleUrls: ['./publisher.component.css']
})
export class PublisherComponent implements OnInit {

  publisher?: Publisher;
  books!: Book[];
  filteredBooks!: Book[];
  imagePath = Constants.IMAGE_PATH;
  filteredBookTitle = "";
  filteredBookAuthor = "";
  filteredBookCategory = "";

  constructor(private route: ActivatedRoute, private bookService: BookService) {
  }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id != null) {
      this.bookService.getPublisherById(id)
      .subscribe(publisher => {
        this.publisher = publisher;
      });

      this.bookService.getBooksByPublisherId(id)
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

    if (this.filteredBookCategory !== "") {
      this.filteredBooks = this.filteredBooks
      .filter(b => b.categories.some(c => {
        return c.name.toLowerCase().includes(this.filteredBookCategory.toLowerCase());
      }));
    }

    if (this.filteredBookTitle === "" && this.filteredBookAuthor === "" && this.filteredBookCategory === "") {
      this.filteredBooks = this.books;
    }
  }
}
