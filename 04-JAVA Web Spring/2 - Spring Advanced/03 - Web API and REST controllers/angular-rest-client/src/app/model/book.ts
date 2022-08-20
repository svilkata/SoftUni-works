import {Author} from "./author";

export interface Book {
  title: string;
  isbn: string;
  authorDTO: Author;
}
