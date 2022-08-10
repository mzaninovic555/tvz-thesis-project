import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../services/authentication.service";
import {RegisterUser} from "../domain/register-user";
import {Router} from "@angular/router";
import {User} from "../domain/user";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  allUsers: User[] = [];

  constructor(private authenticationService: AuthenticationService,
              private router: Router,
              private userService: UserService) {
    this.userService.getAllUsers()
      .subscribe({
        next: (users) => {
          this.allUsers = users;
        }
      })
  }

  ngOnInit(): void {
  }

  registerUser(username: string, password: string, email: string, firstName: string, lastName: string,
               phoneNumber: string, address: string, postalCode: string, city: string) {

    let newUser = new RegisterUser(
        0,
        username,
        password,
        email,
        firstName,
        lastName,
        phoneNumber,
        address,
        postalCode,
        city
    );

    this.authenticationService.registerUser(newUser)
      .subscribe({
        next: value => this.router.navigate(['registrationSuccess'])
      });
  }

}
