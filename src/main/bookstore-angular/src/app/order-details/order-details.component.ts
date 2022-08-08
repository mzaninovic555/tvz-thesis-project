import {Component, OnInit} from '@angular/core';
import {Order} from "../domain/order";
import {ActivatedRoute} from "@angular/router";
import {OrderService} from "../services/order.service";
import {BookService} from "../services/book.service";
import {Book} from "../domain/book";
import {Constants} from "../domain/constants";
import {CartItem} from "../domain/cart-item";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  order!: Order;
  books!: Book[];
  searchOrder!: string;
  orderItems: CartItem[] = []
  imagePath = Constants.IMAGE_PATH;

  constructor(private route: ActivatedRoute,
              private orderService: OrderService,
              private bookService: BookService,
              public authenticationService: AuthenticationService) {
    this.searchOrder = this.route.snapshot.paramMap.get('id') || '';

    this.orderService.getOrderById(this.searchOrder)
      .subscribe({
        next: (order) => {
          this.order = order;
        },
        error: err => console.error(err),
        complete: () => this.fillBooks()
      })
  }

  ngOnInit(): void {
  }

  fillBooks() {
    this.bookService.getBooksByOrderId(this.searchOrder)
      .subscribe({
        next: (books) => {
          this.books = books;
        },
        error: err => console.error(err),
        complete: () => this.fillOrderItems()
      });
  }

  fillOrderItems() {
    let uniqueItems = Array.from(new Set(this.order.books.map(b => b.id)));
    let orderBooks = this.order.books;

    for(let item of uniqueItems) {
      let numberOfElems = orderBooks.filter(b => b.id === item).length;
      let currentBook = orderBooks.filter(b => item === b.id)[0];
      this.orderItems.push(new CartItem(currentBook, numberOfElems));
    }
  }

  fillZeroes(n: number) {
    return ('00000' + n).slice(-5);
  }
}
