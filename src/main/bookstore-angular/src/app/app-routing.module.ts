import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomePageComponent} from "./home-page/home-page.component";
import {BookDetailsComponent} from "./book-details/book-details.component";
import {AuthorComponent} from "./author/author.component";
import {PublisherComponent} from "./publisher/publisher.component";
import {BookSearchComponent} from "./book-search/book-search.component";
import {CategoryComponent} from "./category/category.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";

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
    path: 'category/:id',
    component: CategoryComponent
  },
  {
    path: 'cart',
    component: ShoppingCartComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
