import { Injectable } from '@angular/core';
import {Book} from "./book";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor() { }

  getBooks(): Observable<Book[]> {
    return null;
  }
}
