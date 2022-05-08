import { getMyBooks } from '../api/data.js';
import {html} from '../lib.js';
import { getUserData } from '../util.js';
import { bookCard } from './common.js';

const myBooksTemplate = (books) => html`
<!-- My Books Page ( Only for logged-in users ) -->
<section id="my-books-page" class="my-books">
    <h1>My Books</h1>
    
           <!-- Display ul: with list-items for every user's books (if any) -->
    ${books.length == 0 
            ? html`<p class="no-books">No books in database!</p>`
            : html`<ul class="my-books-list">${books.map(bookCard)}</ul>`
            }    
</section>`;


export async function myBooksPage(ctx) {
    const userData = getUserData();
    const books = await getMyBooks(userData.id);

    ctx.renderProps(myBooksTemplate(books));
}