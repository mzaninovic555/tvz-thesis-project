import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { NavbarContentComponent } from './navbar-content/navbar-content.component';
import { BooksComponent } from './books/books.component';
import { FooterBookstoreComponent } from './footer-bookstore/footer-bookstore.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    NavbarContentComponent,
    BooksComponent,
    FooterBookstoreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
