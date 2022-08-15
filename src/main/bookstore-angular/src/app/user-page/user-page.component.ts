import {Component, OnInit} from '@angular/core';
import {User} from "../domain/user";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../services/user.service";
import {AuthenticationService} from "../services/authentication.service";
import {Order} from "../domain/order";
import {OrderService} from "../services/order.service";
import {Category} from "../domain/category";
import {BookService} from "../services/book.service";
import {Author} from "../domain/author";
import {Publisher} from "../domain/publisher";
import {Book} from "../domain/book";
import {Language} from "../domain/language";
import {Discount} from "../domain/discount";
import {FormControl, FormGroup} from "@angular/forms";

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
  allLanguages: Language[] = [];
  discountAvailableBooks: Book[] = [];
  currentYear = new Date().getFullYear();
  bookImage!: File;
  savedBook!: Book;
  today = new Date();

  range: FormGroup<{ start: FormControl<Date | null>; end: FormControl<Date | null> }> = new FormGroup({
    start: new FormControl<Date | null>(null),
    end: new FormControl<Date | null>(null),
  });

  constructor(private route: ActivatedRoute,
              private router: Router,
              public authenticationService: AuthenticationService,
              private bookService: BookService,
              private userService: UserService,
              private orderService: OrderService) {
    this.searchUsername = this.route.snapshot.paramMap.get('username') || '';

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
        error: err => console.error(err),
        complete: () => this.fillAllLanguages()
      });
    }
  }

  fillAllLanguages() {
    if (this.authenticationService.isUserAdmin()) {
      this.bookService.getAllLanguages()
      .subscribe({
        next: (languages) => {
          this.allLanguages = languages;
        },
        error: err => console.error(err),
        complete: () => this.fillAllBooks()
      });
    }
  }

  fillAllBooks() {
    if (this.authenticationService.isUserAdmin()) {
      this.bookService.getBooks()
      .subscribe({
        next: (allBooks) => {
          this.discountAvailableBooks = allBooks;
        },
        error: err => console.error(err),
        complete: () => {
          this.discountAvailableBooks = this.discountAvailableBooks.filter(b => b.discountPrice === 0 && b.discountExpiration === null);
        }
      });
    }
  }

  fillZeroes(n: number) {
    return ('00000' + n).slice(-5);
  }

  submitBook(title: string, image: any, author: Author, publisher: Publisher, price: number, stock: number,
             description: string, year: string, pageNumber: number, binding: string, categories: Category[],
             language: Language, mass: number, format: string, barcode: string, isbn: string) {
    let book = new Book(
        -100,
        format,
        pageNumber,
        binding,
        mass,
        barcode,
        title,
        price,
        0,
        new Date(),
        description,
        +year,
        stock,
        isbn,
        "-",
        new Date(),
        language,
        author,
        publisher,
        categories,
        []
    );

    this.bookService.addBook(book)
      .subscribe({
        next: (newBook) => {
          this.savedBook = newBook;
        },
        error: err => alert("Nešto je pošlo po krivu"),
        complete: () => this.saveBookImage()
      });
  }

  saveBookImage() {

    let formData = new FormData();
    formData.append('image', this.bookImage);

    this.bookService.addBookImage(this.savedBook.id, formData)
      .subscribe({
        next: value => this.router.navigate([`../../book/${this.savedBook.id}`])
      });
  }

  submitAuthor(firstName: string, lastName: string) {
    let author = new Author(
        0,
        firstName,
        lastName
    );

    this.bookService.addAuthor(author)
    .subscribe({
      next: (newAuthor) => {
        this.router.navigate([`../../author/${newAuthor.id}`]);
      },
      error: err => alert("Nešto je pošlo po krivu")
    });
  }

  submitPublisher(publisherName: string) {
    let publisher = new Publisher(
        0,
        publisherName
    );

    this.bookService.addPublisher(publisher)
    .subscribe({
      next: (newPublisher) => {
        this.router.navigate([`../../publisher/${newPublisher.id}`]);
      },
      error: err => alert("Nešto je pošlo po krivu")
    });
  }

  submitCategory(categoryName: string) {
    let category = new Category(
        0,
        categoryName
    );

    this.bookService.addCategory(category)
    .subscribe({
      next: (newCategory) => {
        this.router.navigate([`../../category/${newCategory.id}`]);
      },
      error: err => alert("Nešto je pošlo po krivu")
    });
  }

  submitDiscount(book: Book, discountPrice: number, startsAt: any, endsAt: any) {
    let discount = new Discount(
        0,
        discountPrice,
        startsAt,
        endsAt,
        book
    );

    this.bookService.addDiscount(discount)
      .subscribe({
        next: savedDiscount => this.router.navigate([`../../book/${discount.book.id}`])
      });
  }

  changeBookImage(event: any) {
    this.bookImage = event.target.files[0];
  }

  submitChangePassword(newPassword: string) {
    if (this.user && this.authenticationService.isUserAuthenticated()) {
      this.bookService.changePassword(newPassword, this.user.id)
        .subscribe({
          next: returnValue => this.router.navigate(["/"])
        })
    }
  }
}
