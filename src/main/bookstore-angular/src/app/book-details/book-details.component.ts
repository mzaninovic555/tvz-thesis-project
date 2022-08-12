import {Component, OnInit} from '@angular/core';
import {Book} from "../domain/book";
import {BookService} from "../services/book.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Constants} from "../domain/constants";
import {User} from "../domain/user";
import {UserService} from "../services/user.service";
import {AuthenticationService} from "../services/authentication.service";
import {Review} from "../domain/review";

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent implements OnInit {

  book!: Book;
  cartAmount!: number;
  imagePath = Constants.IMAGE_PATH;
  reviewAverage!: number;
  loggedInUser!: User;
  isUserLeaveReview: boolean = false;

  constructor(private route: ActivatedRoute,
              private bookService: BookService,
              private router: Router,
              private authenticationService: AuthenticationService,
              private userService: UserService) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id != null) {
      this.bookService.getBookById(id)
        .subscribe({
          next: (book) => {
            this.book = book;
          },
          error: err => console.error(err),
          complete: () => {
            this.book?.categories.sort((a, b) => {
              return a.name.localeCompare(b.name);
            });
            this.reviewAverage = this.book.reviews
              .map(r => r.score)
              .reduce((a, b) => a+b,0) / this.book.reviews.length;
            this.didUserLeaveReview();
          }
        });
    } else {
      console.error(`ID can't be null.`);
    }
  }

  addToCart(bookId: number) {
    let cartItems: number[] = JSON.parse(localStorage.getItem('cart') || '[]');
    let bookQuantity = cartItems.filter(b => b === bookId).length;

    if (this.cartAmount) {
      if (this.cartAmount > this.book!.stock || bookQuantity + this.cartAmount > this.book!.stock) {
        this.cartAmount = this.book!.stock;
      }
      for (const i of new Array(this.cartAmount)) {
        cartItems.push(bookId);
      }
    } else {
      if (bookQuantity + 1 < this.book!.stock) {
        cartItems.push(bookId);
      }
    }

    localStorage.setItem('cart', JSON.stringify(cartItems));
    this.router.navigate(['../'])
      .then(() => {
        window.location.reload();
      });
  }

  didUserLeaveReview() {
    if (this.authenticationService.isUserAuthenticated()) {
      this.userService.getByUsername(this.authenticationService.getAuthenticatedUserUsername())
      .subscribe({
        next: (user) => this.loggedInUser = user,
        complete: () => {
          this.isUserLeaveReview = this.book.reviews.filter(r => r.user.id === this.loggedInUser.id).length > 0;
        }
      })
    }
  }

  newReview(reviewValue: number) {
    if (reviewValue) {
      let newReview = new Review(
          0,
          reviewValue,
          this.loggedInUser,
          this.book
      );

      this.bookService.addReview(newReview).subscribe(() => window.location.reload());
    }
  }
}
