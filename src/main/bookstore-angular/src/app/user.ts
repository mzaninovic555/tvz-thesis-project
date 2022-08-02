import {Order} from "./order";

export class User {


  constructor(id: number, username: string, password: string, email: string, firstName: string, lastName: string, phoneNumber: string, address: string, postalCode: string, city: string) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.postalCode = postalCode;
    this.city = city;
  }

  id: number;
  username: string;
  password: string;
  email: string;
  firstName: string;
  lastName: string;
  phoneNumber: string;
  address: string;
  postalCode: string;
  city: string;
}
