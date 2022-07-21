import { Component, OnInit } from '@angular/core';
import {Book} from "../book";
import {BookService} from "../book.service";
import {ActivatedRoute} from "@angular/router";
import {Constants} from "../constants";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  book?: Book;
  imagePath = Constants.IMAGE_PATH;

  constructor(private route: ActivatedRoute, private bookService: BookService) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id != null) {
      this.bookService.getBookById(id)
        .subscribe(book => {
          this.book = book;
        });
    } else {
      console.error(`ID can't be null.`);
    }
  }
}
