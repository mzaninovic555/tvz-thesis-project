import {Book} from "./book";

export class CartItem {

  book: Book;
  amount: number;

  constructor(book: Book, amount: number) {
    this.book = book;
    this.amount = amount;
  }
}
