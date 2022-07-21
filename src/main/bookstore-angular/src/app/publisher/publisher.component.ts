import {Component, OnInit} from '@angular/core';
import {Book} from "../book";
import {Constants} from "../constants";
import {ActivatedRoute} from "@angular/router";
import {BookService} from "../book.service";
import {Publisher} from "../publisher";

@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.component.html',
  styleUrls: ['./publisher.component.css']
})
export class PublisherComponent implements OnInit {

  publisher?: Publisher;
  books!: Book[];
  imagePath = Constants.IMAGE_PATH;

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
      .subscribe(books => {
        this.books = books;
      });
    } else {
      console.error(`ID can't be null.`);
    }
  }


}
