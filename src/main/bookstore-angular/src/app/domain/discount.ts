import {Book} from "./book";

export class Discount {


  constructor(id: number, discountPrice: number, startedAt: Date, endsAt: Date, book: Book) {
    this.id = id;
    this.discountPrice = discountPrice;
    this.startedAt = startedAt;
    this.endsAt = endsAt;
    this.book = book;
  }

  id: number;
  discountPrice: number;
  startedAt: Date;
  endsAt: Date;
  book: Book;
}
