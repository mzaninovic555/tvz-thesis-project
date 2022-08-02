import { Component, OnInit } from '@angular/core';
import {CartItem} from "../cart-item";
import {BookService} from "../book.service";
import {Book} from "../book";
import {Constants} from "../constants";
import {OrderService} from "../order.service";
import {Order} from "../order";
import {Router} from "@angular/router";
import {User} from "../user";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  localStorageCart: string[] = localStorage['cart'];
  cartItems: CartItem[] = [];
  books!: Book[];
  imagePath = Constants.IMAGE_PATH;

  constructor(private bookService: BookService, private orderService: OrderService, private router: Router) { }

  ngOnInit(): void {

    if (this.localStorageCart) {
      this.bookService.getBooks()
        .subscribe({
          next: (books) => {
            this.books = books;
          },
          error: err => {
            console.error(err);
          },
          complete: () => {
            this.fillCartItems();
          }
        });
    }
  }

  fillCartItems() {

    let uniqueItems = Array.from(new Set(this.localStorageCart)).filter(x => !isNaN(+x));
    let localStorageCartArray = Array.from(this.localStorageCart).filter(x => !isNaN(+x));
    for (let elem of uniqueItems) {
      let numberOfElems = localStorageCartArray.filter(x => +x === +elem).length;
      let book = this.books.filter(b => b.id === +elem)[0];
      this.cartItems.push(new CartItem(book, numberOfElems));
    }

    this.cartItems.sort((a, b) => {
      return b.amount - a.amount;
    });
  }

  amountChanged(book: Book, event: any) {

    let newAmount = event.target.value;
    let localStorageTemp = JSON.parse(localStorage['cart'] || '[]');
    localStorageTemp = localStorageTemp.filter((c: number) => c !== book.id);

    for (const i of new Array(+newAmount)) {
      localStorageTemp.push(book.id);
    }

    localStorage.setItem('cart', JSON.stringify(localStorageTemp));
    location.reload();
  }

  purchaseBooks() {

    if (this.cartItems !== undefined || []) {

      let bookArray: Book[] = [];

      for (let cartItem of this.cartItems) {
        for (let i of new Array(+cartItem.amount)) {
          bookArray.push(cartItem.book);
        }
      }

      let newOrder = new Order(
          0,
          new Date(),
          this.cartItems.reduce((previous, current) =>
            previous + current.book.price, 0
          ),
          new User(
              1,
              "",
              "",
              "",
              "",
              "",
              "",
              "",
              "",
              "",
          ),
          bookArray
      );

      //console.log(newOrder);

      this.orderService.addOrder(newOrder).subscribe();
      //this.router.navigate(['../../']);
    }
  }
}