import {Component, OnInit} from '@angular/core';
import {Book} from "../book";
import {BookService} from "../services/book.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Constants} from "../constants";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  book?: Book;
  cartAmount!: number;
  imagePath = Constants.IMAGE_PATH;

  constructor(private route: ActivatedRoute, private bookService: BookService, private router: Router) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id != null) {
      this.bookService.getBookById(id)
        .subscribe({
          next: (book) => {
            this.book = book;
          },
          error: err => console.error(err),
          complete: () => {
            this.book?.categories.sort((a, b) => {
              return a.name.localeCompare(b.name);
            })
          }
        });
    } else {
      console.error(`ID can't be null.`);
    }
  }

  addToCart(bookId: number) {
    let cartItems: number[] = JSON.parse(localStorage.getItem('cart') || '[]');
    let bookQuantity = cartItems.filter(b => b === bookId).length;

    if (this.cartAmount) {
      if (this.cartAmount > this.book!.stock || bookQuantity + this.cartAmount > this.book!.stock) {
        this.cartAmount = this.book!.stock;
      }
      for (const i of new Array(this.cartAmount)) {
        cartItems.push(bookId);
      }
    } else {
      if (bookQuantity + 1 < this.book!.stock) {
        cartItems.push(bookId);
      }
    }

    localStorage.setItem('cart', JSON.stringify(cartItems));
    this.router.navigate(['../'])
      .then(() => {
        window.location.reload();
      });
  }
}
