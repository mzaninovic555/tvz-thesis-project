import {Component, HostListener, OnInit} from '@angular/core';
import {NavigationEnd, NavigationStart, Router} from "@angular/router";
import {Login} from "../security/login";
import {AuthenticationService} from "../services/authentication.service";
import {JwtToken} from "../domain/jwt-token";
import {filter, Subject, takeUntil} from "rxjs";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  book = "";
  cartItems!: number
  login = new Login('', '');
  authenticationError: boolean = false;
  wasLoggedIn: boolean = false;
  wrongCredentials: boolean = false;

  constructor(private router: Router, public authenticationService: AuthenticationService) {
    this.cartItems = JSON.parse(localStorage.getItem('cart') || '[]').length;
  }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.events
      .pipe(filter(e => e instanceof NavigationStart))
      .subscribe(e => {
        this.wrongCredentials = false;
        this.wasLoggedIn = false;
      });
  }

  @HostListener('document:keypress', ['$event'])
  keyEvent(event: KeyboardEvent) {
    if (event.key === 'Enter') {
      this.searchForBooks();
    }
  }

  searchForBooks() {
    if (this.book !== "") {
      this.router.navigate([`book/search/${this.book}`]);
    }
  }

  loginFunction() {
    this.authenticationError = false;
    this.authenticationService.login(this.login)
      .subscribe({
        next: (loginResponse: JwtToken) => {
          this.wasLoggedIn = true;
          this.wrongCredentials = false;
          this.authenticationService.saveJwtToLocalStorage(loginResponse.jwt);
          this.router.navigate(['/']);
        },
        error: () => {
          this.wrongCredentials = true;
        }
      })
  }

  registerFunction() {
    this.router.navigate(['authentication/register'])
  }
}
