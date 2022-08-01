import { Component, OnInit } from '@angular/core';
import {CartItem} from "../cart-item";

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css']
})
export class ShoppingCartComponent implements OnInit {

  localStorageCart: string[] = localStorage['cart'];
  cartItems: CartItem[] = [];

  constructor() { }

  ngOnInit(): void {

    if (this.localStorageCart) {
      let uniqueItems = Array.from(new Set(this.localStorageCart));
      uniqueItems = uniqueItems.filter(x => !isNaN(+x));

      let localStorageCartArray = Array.from(this.localStorageCart);
      localStorageCartArray = localStorageCartArray.filter(x => !isNaN(+x));
      for (let elem of uniqueItems) {
        let numberOfElems = localStorageCartArray.filter(x => +x === +elem).length;

        this.cartItems.push(new CartItem(+elem, numberOfElems));
      }

      console.log(this.cartItems);
    }
  }

}
