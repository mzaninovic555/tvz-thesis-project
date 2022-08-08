import { Component, OnInit } from '@angular/core';
import {CartItem} from "../domain/cart-item";
import {BookService} from "../services/book.service";
import {Book} from "../domain/book";
import {Constants} from "../domain/constants";
import {OrderService} from "../services/order.service";
import {Order} from "../domain/order";
import {Router} from "@angular/router";
import {User} from "../domain/user";
import {AuthenticationService} from "../services/authentication.service";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  localStorageCart: string[] = localStorage['cart'];
  cartItems: CartItem[] = [];
  books!: Book[];
  totalCartPrice: number = 0.00;
  imagePath = Constants.IMAGE_PATH;
  loggedInUser?: User;
  isUserLoggedIn!: boolean;

  constructor(private bookService: BookService,
              private orderService: OrderService,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService) { }

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
            this.isUserLoggedIn = this.authenticationService.isUserAuthenticated();
            this.fetchLoggedInUser();
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

    this.totalCartPrice = this.cartItems.reduce((previous, current) =>
        current.book.discountPrice !== 0 ? previous + (current.book.discountPrice * current.amount) : previous + (current.book.price * current.amount), 0
    )

    this.cartItems.sort((a, b) => {
      return b.amount - a.amount;
    });
  }

  fetchLoggedInUser() {

    if (this.isUserLoggedIn) {
      let username = this.authenticationService.getAuthenticatedUserUsername();

      this.userService.getByUsername(username)
        .subscribe({
          next: user => {
            this.loggedInUser = user;
          }
        });
    }
  }

  amountChanged(book: Book, event: any) {

    let newAmount = event.target.value;
    if (newAmount > book.stock) {
      newAmount = book.stock;
    }
    let localStorageTemp = JSON.parse(localStorage['cart'] || '[]');
    localStorageTemp = localStorageTemp.filter((c: number) => c !== book.id);

    for (const i of new Array(+newAmount)) {
      localStorageTemp.push(book.id);
    }

    localStorage.setItem('cart', JSON.stringify(localStorageTemp));
    location.reload();
  }

  purchaseBooks() {

    if (this.cartItems !== undefined && this.cartItems !== [] && this.loggedInUser !== undefined) {

      let bookArray: Book[] = [];
      for (let cartItem of this.cartItems) {
        for (let i of new Array(+cartItem.amount)) {
          bookArray.push(cartItem.book);
        }
      }

      let newOrder = new Order(
          0,
          new Date(),
          this.totalCartPrice,
          this.loggedInUser,
          bookArray
      );

      this.orderService.addOrder(newOrder).subscribe({
        next: () => {
          localStorage.removeItem('cart');
          this.router.navigate([`../`])
            .then(() => {
              window.location.reload();
          });
        }
      });
    }
  }
}
