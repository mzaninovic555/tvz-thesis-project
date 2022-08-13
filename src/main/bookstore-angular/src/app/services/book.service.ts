import {Injectable} from '@angular/core';
import {Book} from "../domain/book";
import {catchError, Observable, of, tap} from "rxjs";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Author} from "../domain/author";
import {Constants} from "../domain/constants";
import {Publisher} from "../domain/publisher";
import {Category} from "../domain/category";
import {Language} from "../domain/language";
import {Discount} from "../domain/discount";
import {Review} from "../domain/review";
import {DomSanitizer} from "@angular/platform-browser";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  private URL = Constants.SPRING_URL;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) { }

  bypassImageSecurity(book: Book) {
    return this.sanitizer.bypassSecurityTrustResourceUrl('data:image/png;base64,' + book.imagePath);
  }

  //GET REQUESTS

  getBooks(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/books`)
    .pipe(
        tap(_ => console.log('Fetched books')),
        catchError(this.handleError<Book[]>('getBooks', []))
    );
  }

  getBooksOriginalImages(): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/books/original-images`)
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

  getAllAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>(`${this.URL}/author/all`)
    .pipe(
        tap(_ => console.log('Fetched authors')),
        catchError(this.handleError<Author[]>('getAllAuthors'))
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

  getAllPublishers(): Observable<Publisher[]> {
    return this.http.get<Publisher[]>(`${this.URL}/publisher/all`)
    .pipe(
        tap(_ => console.log('Fetched Publishers')),
        catchError(this.handleError<Publisher[]>('getAllPublishers'))
    );
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

  getBooksByOrderId(id: string): Observable<Book[]> {
    return this.http.get<Book[]>(`${this.URL}/api/books/order/${id}`)
    .pipe(
        tap(_ => console.log('Fetched books by category id')),
        catchError(this.handleError<Book[]>('getBooksWithCategoryId'))
    );
  }

  getAllLanguages(): Observable<Language[]> {
    return this.http.get<Language[]>(`${this.URL}/api/languages/all`)
    .pipe(
        tap(_ => console.log('Fetched all Languages')),
        catchError(this.handleError<Language[]>('getAllLanguages'))
    );
  }


  //POST REQUESTS

  addBook(book: Book): Observable<Book> {
    return this.http.post<Book>(`${this.URL}/api/add/book`, book, this.httpOptions)
    .pipe(
        tap((newBook: Book) => console.log(`added book ${newBook}`)),
        catchError(this.handleError<Book>('addBook'))
    );
  }

  addAuthor(author: Author): Observable<Author> {
    return this.http.post<Author>(`${this.URL}/api/add/author`, author, this.httpOptions)
    .pipe(
        tap((newAuthor: Author) => console.log(`added Author ${newAuthor}`)),
        catchError(this.handleError<Author>('addAuthor'))
    );
  }

  addPublisher(publisher: Publisher): Observable<Publisher> {
    return this.http.post<Publisher>(`${this.URL}/api/add/publisher`, publisher, this.httpOptions)
    .pipe(
        tap((newPublisher: Publisher) => console.log(`added Publisher ${newPublisher}`)),
        catchError(this.handleError<Publisher>('addPublisher'))
    );
  }

  addCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(`${this.URL}/api/add/category`, category, this.httpOptions)
    .pipe(
        tap((newCategory: Category) => console.log(`added Category ${newCategory}`)),
        catchError(this.handleError<Category>('addCategory'))
    );
  }

  addDiscount(discount: Discount): Observable<Discount> {
    return this.http.post<Discount>(`${this.URL}/api/add/discount`, discount, this.httpOptions)
    .pipe(
        tap((newCategory: Discount) => console.log(`added Discount ${newCategory}`)),
        catchError(this.handleError<Discount>('addDiscount'))
    );
  }

  addBookImage(bookId: number, bookImage: FormData): Observable<any> {
    console.log(bookImage);
    return this.http.post(`${this.URL}/api/add/book/image/${bookId}`, bookImage);
  }

  addReview(review: Review): Observable<Review> {
    return this.http.post<Review>(`${this.URL}/api/add/review`, review, this.httpOptions)
    .pipe(
        tap((newreview: Review) => console.log(`added review ${newreview}`)),
        catchError(this.handleError<Review>('addReview'))
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
