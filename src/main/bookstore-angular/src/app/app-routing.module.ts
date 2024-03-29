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
import {AuthenticationGuard} from "./security/authentication-guard.service";
import {OrderDetailsComponent} from "./order-details/order-details.component";
import {InternalErrorComponent} from "./internal-error/internal-error.component";
import {BadRequestComponent} from "./bad-request/bad-request.component";
import {RegisterComponent} from "./register/register.component";
import {RegistrationSuccessComponent} from "./registration-success/registration-success.component";

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
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'order/:id',
    component: OrderDetailsComponent,
    canActivate: [AuthenticationGuard]
  },
  {
    path: 'authentication/register',
    component: RegisterComponent
  },
  {
    path: 'forbidden',
    component: ForbiddenComponent
  },
  {
    path: 'notFound',
    component: PageNotFoundComponent
  },
  {
    path: 'internalError',
    component: InternalErrorComponent
  },
  {
    path: 'badRequest',
    component: BadRequestComponent
  },
  {
    path: 'registrationSuccess',
    component: RegistrationSuccessComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
