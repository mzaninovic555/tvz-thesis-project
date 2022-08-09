import {Component, HostListener, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Login} from "../security/login";
import {AuthenticationService} from "../services/authentication.service";
import {JwtToken} from "../domain/jwt-token";

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

  constructor(private router: Router, public authenticationService: AuthenticationService) {
    this.cartItems = JSON.parse(localStorage.getItem('cart') || '[]').length;
  }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
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
          this.authenticationService.saveJwtToLocalStorage(loginResponse.jwt);
          window.location.reload()
        },
        error: () => {
          alert("Krivo korisničko ime ili lozinka.")
        }
      })
  }

  registerFunction() {
    this.router.navigate(['authentication/register'])
  }
}
