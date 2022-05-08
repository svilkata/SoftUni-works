import { searchBooks } from '../api/data.js';
import {html} from '../lib.js';
import { bookCard } from './common.js';

const searchTemplate = (books, onSearch, params = '') => html`
<!-- Dashboard Page ( for Guests and Users ) -->
<section id="search-page" class="dashboard">
    <h1>Search</h1>
        <form @submit=${onSearch}>
            <input type="text" name="search" .value=${params}>
            <input type="submit" value="Search">
        </form>

        ${books.length == 0 
        ? html`<p class="no-books">No results found/p>`
        : html`<ul class="other-books-list">${books.map(bookCard)}</ul>` 
        }
</section>`;

export async function searchPage(ctx) {
    const params = ctx.querystring.split('=')[1];
    let books = [];

    if (params) {
        books = await searchBooks(decodeURIComponent(params));
    }

    ctx.renderProps(searchTemplate(books, onSearch, params));

    function onSearch(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const search = formData.get('search');

        if (search != '') {
            ctx.page.redirect('search?query=' + encodeURIComponent(search));            
            console.log(search);
        }
    }
}