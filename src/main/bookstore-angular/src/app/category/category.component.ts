import { Component, OnInit } from '@angular/core';
import {Category} from "../category";
import {Book} from "../book";
import {Constants} from "../constants";
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../book.service";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  category!: Category;
  books!: Book[];
  filteredBooks!: Book[];
  imagePath = Constants.IMAGE_PATH;
  filteredBookTitle = "";
  filteredBookAuthor = "";
  filteredBookPublisher = "";

  constructor(private route: ActivatedRoute, private bookService: BookService) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id != null) {
      this.bookService.getCategoryById(id)
      .subscribe(category => {
        this.category = category;
      });

      this.bookService.getBooksWithCategoryId(id)
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
