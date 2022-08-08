import {Injectable} from '@angular/core';
import {Constants} from "../constants";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {User} from "../user";

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

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(operation);
      console.error(error);
      return of(result as T);
    };
  }
}
