import {Injectable} from '@angular/core';
import {Book} from "./book";
import {catchError, Observable, of, tap} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Author} from "./author";
import {Constants} from "./constants";
import {Publisher} from "./publisher";
import {Category} from "./category";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private URL = Constants.SPRING_URL;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/books`)
    .pipe(
        tap(_ => console.log('Fetched books')),
        catchError(this.handleError<Book[]>('getBooks', []))
    );
  }

  getBookById(id: string): Observable<Book> {
    return this.http.get<Book>(`${this.URL}/books/${id}`)
    .pipe(
        tap(_ => console.log('Fetched books')),
        catchError(this.handleError<Book>('getBook'))
    );
  }

  getBooksByTitle(searchTerm: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/api/books/search-by-title/${searchTerm}`)
    .pipe(
        tap(_ => console.log('Fetched books by title')),
        catchError(this.handleError<Book[]>('getBooksByTitle'))
    );
  }

  getBooksByAuthorId(id: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/api/books/author/${id}`)
    .pipe(
        tap(_ => console.log('Fetched books by author id')),
        catchError(this.handleError<Book[]>('getBookByAuthorId'))
    );
  }

  getAuthorById(id: string): Observable<Author> {
    return this.http.get<Author>(`${this.URL}/author/${id}`)
      .pipe(
          tap(_ => console.log('Fetched author by id')),
          catchError(this.handleError<Author>('getAuthorById'))
      )
  }

  getBooksByPublisherId(id: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/api/books/publisher/${id}`)
      .pipe(
          tap(_ => console.log('Fetched books by Publisher id')),
          catchError(this.handleError<Book[]>('getBookByPublisherId'))
      );
  }

  getPublisherById(id: string): Observable<Publisher> {
    return this.http.get<Publisher>(`${this.URL}/publisher/${id}`)
      .pipe(
          tap(_ => console.log('Fetched Publisher by id')),
          catchError(this.handleError<Publisher>('getPublisherById'))
      );
  }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.URL}/category`)
    .pipe(
        tap(_ => console.log('Fetched all categories')),
        catchError(this.handleError<Category[]>('getAllCategories'))
    );
  }

  getBooksWithCategoryId(id: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/api/books/category/${id}`)
      .pipe(
          tap(_ => console.log('Fetched books by category id')),
          catchError(this.handleError<Book[]>('getBooksWithCategoryId'))
      );
  }

  getCategoryById(id: string): Observable<Category> {
    return this.http.get<Category>(`${this.URL}/category/${id}`)
      .pipe(
          tap(_ => console.log('Fetched category by id')),
          catchError(this.handleError<Category>('getCategoryById'))
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
