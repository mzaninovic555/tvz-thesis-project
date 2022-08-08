import { Component, OnInit } from '@angular/core';
import {User} from "../user";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../services/user.service";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user!: User;
  searchTerm!: string;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private authenticationService: AuthenticationService) {
    this.searchTerm = this.route.snapshot.paramMap.get("searchTerm") || "";

    this.userService.getByUsername(authenticationService.getAuthenticatedUserUsername())
      .subscribe({
        next: user => {
          this.user = user;
        }
      })
  }

  ngOnInit(): void {
  }

}
