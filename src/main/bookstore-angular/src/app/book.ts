import {Author} from "./author";
import {Category} from "./category";
import {Publisher} from "./publisher";
import {DatePipe} from "@angular/common";

export interface Book {

  id: number;
  format: string;
  pageNumber: number;
  binding: string;
  mass: number;
  barcode: string;
  title: string;
  price: number;
  description: string;
  publishingYear: number;
  stock: number;
  isDiscount: boolean;
  isbn: string;
  imagePath: string;
  dateAdded: DatePipe;
  language: string;
  author: Author;
  publisher: Publisher;
  categories: Category[];
}
