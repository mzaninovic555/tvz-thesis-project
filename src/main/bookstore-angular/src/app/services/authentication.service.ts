import {Injectable} from '@angular/core';
import {Constants} from "../domain/constants";
import {HttpClient} from "@angular/common/http";
import {Login} from "../security/login";
import {JwtToken} from "../domain/jwt-token";
import jwt_decode from "jwt-decode";
import {Router} from "@angular/router";
import {User} from "../domain/user";
import {RegisterUser} from "../domain/register-user";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  authenticationUrl = Constants.SPRING_URL + '/authentication'

  constructor(private http: HttpClient, private router: Router) { }

  login(login: Login) {
    return this.http.post<JwtToken>(`${this.authenticationUrl}/login`, login);
  }

  registerUser(newUser: RegisterUser) {
    return this.http.post<RegisterUser>(`${this.authenticationUrl}/register`, newUser);
  }

  saveJwtToLocalStorage(jwt: string) {
    localStorage.setItem('accessToken', jwt);
  }

  isUserAuthenticated() {
    const decodedToken = this.decodeJwt();
    let usernameKey = null;

    if (decodedToken != null) {
      usernameKey = (Object.keys(decodedToken) as (keyof typeof decodedToken)[])
        .find((key) => {
          return key === 'sub' && decodedToken[key] != null;
      });
    }

    return usernameKey != null;
  }

  getAuthenticatedUserUsername() {
    const decodedToken = this.decodeJwt();
    let username = null;

    if (decodedToken != null) {
      const usernameKey = (Object.keys(decodedToken) as (keyof typeof decodedToken)[])
        .find((key) => {
          return key === 'sub' && decodedToken[key] != null;
        });
      if (usernameKey != null) {
        username = decodedToken[usernameKey]
      }
    }

    return username;
  }

  isUserAdmin(): boolean {
    const adminRoleName = 'ROLE_ADMIN';
    const decodedToken = this.decodeJwt();
    let authorities: any = null;

    if (decodedToken != null) {
      const usernameKey = (Object.keys(decodedToken) as (keyof typeof decodedToken)[])
        .find((key) => {
        // @ts-ignore
          return key === 'authorities' && decodedToken[key] != null;
        });

      if (usernameKey != null) {
        authorities = decodedToken[usernameKey]
      }
    }

    return authorities != null && authorities.includes(adminRoleName);
  }

  logout() {
    localStorage.removeItem('accessToken');
    this.router.navigate(['/']);
  }

  decodeJwt(): string | null {
    let token = localStorage.getItem('accessToken');
    if (token != null) {
      return jwt_decode(token);
    } else {
      return null;
    }
  }
}
