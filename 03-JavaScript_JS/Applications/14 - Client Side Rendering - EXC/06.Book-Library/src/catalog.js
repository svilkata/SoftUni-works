import {deleteBook, getBooks, html, until} from './utility.js';

//list module:
// display list of books
// control books(edit, delete)

const catalogTemplate = (booksPromise) => html`
<table>
    <thead>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        ${until(booksPromise, html`<tr><td colSpan = "3">Loading&hellip;</td></tr>`)}
    </tbody>
</table>`;

const bookRow = (book, onEdit, onDelete) => html`
<tr>
    <td>${book.title}</td>
    <td>${book.author}</td>
    <td>
        <button @click=${onEdit}>Edit</button>
        <button @click=${onDelete}>Delete</button>
    </td>
</tr>`;

export function showCatalog(ctx){
    return catalogTemplate(loadBooks(ctx));
}

async function loadBooks(ctx) {
    const data = await getBooks();
    
    const books = Object.entries(data).map(([k, v]) => Object.assign(v, {_id: k}));
    console.log(books);

    return books.map(bk => bookRow(bk, toggleEditor.bind(null, bk, ctx), onDelete.bind(null, bk._id, ctx)));
}

function toggleEditor(book, ctx) {
    ctx.book = book; //задаваме ново текущо свойство на ctx - да има book
    ctx.updateRender();
}

async function onDelete(id, ctx) {
    await deleteBook(id);
    ctx.updateRender();
}