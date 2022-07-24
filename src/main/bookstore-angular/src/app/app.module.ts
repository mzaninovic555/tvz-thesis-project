import {NgModule, SecurityContext} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NavbarContentComponent } from './navbar-content/navbar-content.component';
import { BooksComponent } from './books/books.component';
import { FooterBookstoreComponent } from './footer-bookstore/footer-bookstore.component';
import { HomePageComponent } from './home-page/home-page.component';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import { BookDetailsComponent } from './book-details/book-details.component';
import {MarkdownModule} from "ngx-markdown";
import { AuthorComponent } from './author/author.component';
import { PublisherComponent } from './publisher/publisher.component';
import {FormsModule} from "@angular/forms";
import { BookSearchComponent } from './book-search/book-search.component';

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
    BookSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MarkdownModule,
    MarkdownModule.forRoot({loader: HttpClient, sanitize: SecurityContext.NONE}),
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
