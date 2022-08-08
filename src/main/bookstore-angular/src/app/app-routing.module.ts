import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./home-page/home-page.component";
import {BookDetailsComponent} from "./book-details/book-details.component";
import {AuthorComponent} from "./author/author.component";
import {PublisherComponent} from "./publisher/publisher.component";
import {BookSearchComponent} from "./book-search/book-search.component";
import {CategoryComponent} from "./category/category.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {ForbiddenComponent} from "./forbidden/forbidden.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import {AllBooksComponent} from "./all-books/all-books.component";

const routes: Routes = [
  {
    path: '',
    component: HomePageComponent
  },
  {
    path: 'book/:id',
    component: BookDetailsComponent
  },
  {
    path: 'author/:id',
    component: AuthorComponent
  },
  {
    path: 'publisher/:id',
    component: PublisherComponent
  },
  {
    path: 'book/search/:searchTerm',
    component: BookSearchComponent
  },
  {
    path: 'books/all',
    component: AllBooksComponent
  },
  {
    path: 'category/:id',
    component: CategoryComponent
  },
  {
    path: 'cart',
    component: ShoppingCartComponent
  },
  {
    path: 'forbidden',
    component: ForbiddenComponent
  },
  {
    path: '**',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
