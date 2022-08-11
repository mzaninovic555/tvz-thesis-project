import {Book} from "./book";

export class Review {

  constructor(id: number, score: number, userId: number) {
    this.id = id;
    this.score = score;
    this.userId = userId;
  }

  id: number;
  score: number;
  userId: number;
}
