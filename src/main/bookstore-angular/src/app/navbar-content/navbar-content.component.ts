import {Component, OnInit} from '@angular/core';
import {BookService} from "../services/book.service";
import {Category} from "../category";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar-content',
  templateUrl: './navbar-content.component.html',
  styleUrls: ['./navbar-content.component.css']
})
export class NavbarContentComponent implements OnInit {

  categories!: Category[];
  isLoaded: boolean = false;

  constructor(private bookService: BookService, private router: Router) { }
  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

    this.bookService.getAllCategories()
      .subscribe({
        next: categories => {
          this.categories = categories;
        },
        error: err => console.log(err),
        complete: () => {
          this.isLoaded = true;
        }
      });
  }

  filterByCategory(category: string): number {
    if (this.isLoaded) {
      let cat = this.categories.filter(c => c.name === category)[0]?.id;

      if (cat !== undefined)
        return cat
    }

    return 0;
  }

  allBooks() {
    this.router.navigate([`book/all/`]);
  }
}
