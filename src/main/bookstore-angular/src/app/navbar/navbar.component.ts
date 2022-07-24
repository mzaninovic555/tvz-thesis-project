import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Constants} from "../constants";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  book = "";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  searchForBooks() {
    if (this.book !== "") {
      this.router.navigate([`book/search/${this.book}`]);
    }
  }

}
