import {Book} from "./book";
import {User} from "./user";

export class Order {

  constructor(id: number, datePlaced: Date, totalPrice: number, user: User, books: Book[]) {
    this.id = id;
    this.datePlaced = datePlaced;
    this.totalPrice = totalPrice;
    this.user = user;
    this.books = books;
  }

  id: number;
  datePlaced: Date;
  totalPrice: number;
  user: User;
  books: Book[];
}
