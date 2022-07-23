import {Author} from "./author";
import {Category} from "./category";
import {Publisher} from "./publisher";

export interface Book {

  id: number;
  format: string;
  pageNumber: number;
  binding: string;
  mass: number;
  barcode: string;
  title: string;
  price: number;
  discountPrice: number;
  discountExpiration: Date;
  description: string;
  publishingYear: number;
  stock: number;
  isbn: string;
  imagePath: string;
  dateAdded: Date;
  language: string;
  author: Author;
  publisher: Publisher;
  categories: Category[];
}
