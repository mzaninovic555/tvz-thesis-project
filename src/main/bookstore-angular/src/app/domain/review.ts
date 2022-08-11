import {Book} from "./book";
import {User} from "./user";

export class Review {

  constructor(id: number, score: number, user: User, book: Book) {
    this.id = id;
    this.score = score;
    this.user = user;
    this.book = book;
  }

  id: number;
  score: number;
  user: User;
  book: Book;
}
