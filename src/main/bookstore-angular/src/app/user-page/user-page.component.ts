import {Component, OnInit} from '@angular/core';
import {User} from "../domain/user";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../services/user.service";
import {AuthenticationService} from "../services/authentication.service";
import {Order} from "../domain/order";
import {OrderService} from "../services/order.service";

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {

  user!: User;
  searchUsername!: string;
  orders!: Order[];

  constructor(private route: ActivatedRoute,
              private authenticationService: AuthenticationService,
              private userService: UserService,
              private orderService: OrderService) {
    this.searchUsername = this.route.snapshot.paramMap.get('username') || '';

    console.log(this.searchUsername)

    this.userService.getByUsername(this.searchUsername)
      .subscribe({
        next: user => {
          this.user = user;
        },
        error: err => console.error(err),
        complete: () => this.fillOrdersByUserId()
      })
  }

  ngOnInit(): void {
  }

  fillOrdersByUserId() {

    this.orderService.getOrdersByUserId(this.user.id)
      .subscribe({
        next: (orders) => {
          this.orders = orders;
        }
      });
  }

  fillZeroes(n: number) {
    return ('00000' + n).slice(-5);
  }
}
