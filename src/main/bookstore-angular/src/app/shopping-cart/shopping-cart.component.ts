import { Component, OnInit } from '@angular/core';
import {CartItem} from "../cart-item";
import {BookService} from "../book.service";
import {Book} from "../book";
import {Constants} from "../constants";

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

  constructor(private bookService: BookService) { }

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
    console.log(this.cartItems);
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
    alert("Uspješna kupnja");
  }
}
