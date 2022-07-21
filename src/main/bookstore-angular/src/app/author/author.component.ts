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
  imagePath = Constants.IMAGE_PATH;

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
      .subscribe(books => {
        this.books = books;
      });
    } else {
      console.error(`ID can't be null.`);
    }
  }

}
