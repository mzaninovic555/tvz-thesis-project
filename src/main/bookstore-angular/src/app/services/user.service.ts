import {Injectable} from '@angular/core';
import {Constants} from "../domain/constants";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {User} from "../domain/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {


  private URL = Constants.SPRING_URL;

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(private http: HttpClient) { }

  getByUsername(username: any): Observable<User> {
    return this.http.get<User>(`${this.URL}/user/${username}`, this.httpOptions)
    .pipe(
        tap((user: User) => console.log(`get user w username=${user.username}`)),
        catchError(this.handleError<User>('getUserByUsername'))
    );
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.URL}/user/all`, this.httpOptions)
    .pipe(
        tap((users: User[]) => console.log(`get all users ${users}`)),
        catchError(this.handleError<User[]>('getUsers'))
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
