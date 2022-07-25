import {Component, OnInit} from '@angular/core';
import {BookService} from "../book.service";
import {Category} from "../category";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar-content',
  templateUrl: './navbar-content.component.html',
  styleUrls: ['./navbar-content.component.css']
})
export class NavbarContentComponent implements OnInit {

  categories!: Category[];

  constructor(private bookService: BookService, private router: Router) { }
  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

    this.bookService.getAllCategories()
      .subscribe({
        next: categories => {
          this.categories = categories;
        }
      });
  }

  filterByCategory(category: string): number {
    for (const categoryElement of category) {

    }
    return this.categories.filter(c => c.name === category)[0].id;
  }
}
