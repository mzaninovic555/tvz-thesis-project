import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
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
import {UserPageComponent} from "./user-page/user-page.component";
import {LoggedInGuard} from "./security/logged-in-guard";
import {OrderDetailsComponent} from "./order-details/order-details.component";

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
    path: 'user/:username',
    component: UserPageComponent,
    canActivate: [LoggedInGuard]
  },
  {
    path: 'order/:id',
    component: OrderDetailsComponent,
    canActivate: [LoggedInGuard]
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
