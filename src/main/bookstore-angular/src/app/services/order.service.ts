import { Injectable } from '@angular/core';
import {Constants} from "../constants";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {Order} from "../order";
import {User} from "../user";
import {Book} from "../book";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private URL = Constants.SPRING_URL;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  addOrder(order: Order): Observable<Order> {
    return this.http.post<Order>(`${this.URL}/api/orders/add`, order, this.httpOptions)
    .pipe(
        tap((newOrder: Order) => console.log(`added Order w code=${newOrder.id}`)),
        catchError(this.handleError<Order>('addOrder'))
    );
  }

  getOrdersByUserId(userId: number): Observable<User[]> {
    return this.http.get<User[]>(`${this.URL}api/orders/user/${userId}`)
    .pipe(
        tap(_ => console.log('Fetched orders by user id')),
        catchError(this.handleError<User[]>('getOrdersByUserId'))
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
