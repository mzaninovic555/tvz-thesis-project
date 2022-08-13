import {Author} from "./author";
import {Category} from "./category";
import {Publisher} from "./publisher";
import {Language} from "./language";
import {Review} from "./review";

export class Book {

  constructor(id: number, format: string, pageNumber: number, binding: string, mass: number, barcode: string,
              title: string, price: number, discountPrice: number, discountExpiration: Date, description: string,
              publishingYear: number, stock: number, isbn: string, imagePath: string, dateAdded: Date, language: Language,
              author: Author, publisher: Publisher, categories: Category[], reviews: Review[]) {
    this.id = id;
    this.format = format;
    this.pageNumber = pageNumber;
    this.binding = binding;
    this.mass = mass;
    this.barcode = barcode;
    this.title = title;
    this.price = price;
    this.discountPrice = discountPrice;
    this.discountExpiration = discountExpiration;
    this.description = description;
    this.publishingYear = publishingYear;
    this.stock = stock;
    this.isbn = isbn;
    this.imagePath = imagePath;
    this.dateAdded = dateAdded;
    this.language = language;
    this.author = author;
    this.publisher = publisher;
    this.categories = categories;
    this.reviews = reviews;
  }

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
  imagePath: any;
  dateAdded: Date;
  language: Language;
  author: Author;
  publisher: Publisher;
  categories: Category[];
  reviews: Review[];
}
