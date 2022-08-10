import {NgModule, SecurityContext} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {NavbarContentComponent} from './navbar-content/navbar-content.component';
import {BooksComponent} from './books/books.component';
import {FooterBookstoreComponent} from './footer-bookstore/footer-bookstore.component';
import {HomePageComponent} from './home-page/home-page.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {BookDetailsComponent} from './book-details/book-details.component';
import {MarkdownModule} from "ngx-markdown";
import {AuthorComponent} from './author/author.component';
import {PublisherComponent} from './publisher/publisher.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BookSearchComponent} from './book-search/book-search.component';
import {CategoryComponent} from './category/category.component';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ForbiddenComponent} from './forbidden/forbidden.component';
import {AuthenticationInterceptor} from "./security/authentication-interceptor";
import {AllBooksComponent} from './all-books/all-books.component';
import {UserPageComponent} from './user-page/user-page.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { InternalErrorComponent } from './internal-error/internal-error.component';
import { BadRequestComponent } from './bad-request/bad-request.component';
import { RegisterComponent } from './register/register.component';
import { RegistrationSuccessComponent } from './registration-success/registration-success.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatInputModule} from "@angular/material/input";
import {MatNativeDateModule} from "@angular/material/core";
import {ExistingUsernameValidator} from "./validators/existing-username-validator.directive";
import {EmailInUseValidator} from "./validators/email-in-use-validator";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NavbarContentComponent,
    BooksComponent,
    FooterBookstoreComponent,
    HomePageComponent,
    BookDetailsComponent,
    AuthorComponent,
    PublisherComponent,
    BookSearchComponent,
    CategoryComponent,
    ShoppingCartComponent,
    PageNotFoundComponent,
    ForbiddenComponent,
    AllBooksComponent,
    UserPageComponent,
    OrderDetailsComponent,
    InternalErrorComponent,
    BadRequestComponent,
    RegisterComponent,
    RegistrationSuccessComponent,
    ExistingUsernameValidator,
    EmailInUseValidator
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MarkdownModule,
    MarkdownModule.forRoot({loader: HttpClient, sanitize: SecurityContext.NONE}),
    FormsModule,
    MatFormFieldModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

  constructor() {}
}
