import {Component, OnInit} from '@angular/core';
import {User} from "../domain/user";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {AuthenticationService} from "../services/authentication.service";
import {Order} from "../domain/order";
import {OrderService} from "../services/order.service";
import {FormControl} from "@angular/forms";
import {Category} from "../domain/category";
import {BookService} from "../services/book.service";
import {Author} from "../domain/author";
import {Publisher} from "../domain/publisher";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user!: User;
  searchUsername!: string;
  orders!: Order[];
  allUsers!: User[];
  allAuthors: Author[] = [];
  allCategories: Category[] = [];
  allPublishers: Publisher[] = [];
  categories = new FormControl('');

  constructor(private route: ActivatedRoute,
              private router: Router,
              public authenticationService: AuthenticationService,
              private bookService: BookService,
              private userService: UserService,
              private orderService: OrderService) {
    this.searchUsername = this.route.snapshot.paramMap.get('username') || '';

    console.log(this.searchUsername)

    this.userService.getByUsername(this.searchUsername)
      .subscribe({
        next: user => {
          this.user = user;
        },
        error: err => console.error(err),
        complete: () => this.fillOrdersByUserId()
      })
  }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  fillOrdersByUserId() {
    this.orderService.getOrdersByUserId(this.user.id)
      .subscribe({
        next: (orders) => {
          this.orders = orders;
        },
        error: err => console.error(err),
        complete: () => this.fillAllUsers()
      });
  }

  fillAllUsers() {
    if (this.authenticationService.isUserAdmin()) {
      this.userService.getAllUsers()
        .subscribe({
          next: (allUsers) => {
            this.allUsers = allUsers;
          },
          error: err => console.error(err),
          complete: () => this.fillAllAuthors()
        });
    }
  }

  fillAllAuthors() {
    if (this.authenticationService.isUserAdmin()) {
      this.bookService.getAllAuthors()
      .subscribe({
        next: (authors) => {
          this.allAuthors = authors;
        },
        error: err => console.error(err),
        complete: () => this.fillAllCategories()
      });
    }
  }

  fillAllCategories() {
    if (this.authenticationService.isUserAdmin()) {
      this.bookService.getAllCategories()
      .subscribe({
        next: (categories) => {
          this.allCategories = categories;
        },
        error: err => console.error(err),
        complete: () => this.fillAllPublishers()
      });
    }
  }

  fillAllPublishers() {
    if (this.authenticationService.isUserAdmin()) {
      this.bookService.getAllPublishers()
      .subscribe({
        next: (publishers) => {
          this.allPublishers = publishers;
        },
        error: err => console.error(err)
      });
    }
  }

  fillZeroes(n: number) {
    return ('00000' + n).slice(-5);
  }
}
