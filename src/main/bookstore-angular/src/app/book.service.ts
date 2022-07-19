import {Injectable} from '@angular/core';
import {Book} from "./book";
import {catchError, Observable, of, tap} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private bookURL = 'http://localhost:8080/books';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  getBooks(): Observable<Book[]> {

    return this.http.get<Book[]>(this.bookURL)
    .pipe(
        tap(_ => console.log('Fetched books')),
        catchError(this.handleError<Book[]>('getBooks', []))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
  }
}
