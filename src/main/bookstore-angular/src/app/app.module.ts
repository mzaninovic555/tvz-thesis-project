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
import {FormsModule} from "@angular/forms";
import {BookSearchComponent} from './book-search/book-search.component';
import {CategoryComponent} from './category/category.component';
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {PageNotFoundComponent} from './page-not-found/page-not-found.component';
import {ForbiddenComponent} from './forbidden/forbidden.component';
import {AuthenticationInterceptor} from "./security/authentication-interceptor";
import {AllBooksComponent} from './all-books/all-books.component';
import {UserPageComponent} from './user-page/user-page.component';
import { OrderDetailsComponent } from './order-details/order-details.component';

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
    OrderDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MarkdownModule,
    MarkdownModule.forRoot({loader: HttpClient, sanitize: SecurityContext.NONE}),
    FormsModule
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
