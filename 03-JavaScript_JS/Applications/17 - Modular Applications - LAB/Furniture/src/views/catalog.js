import { html, until } from "../lib.js";
import { getAll, getMyItems } from "../api/data.js";
import { getUserData, parseQueryString } from "../util.js";

const catalogTemplate = (dataPromise, userpage, onSearch, search) => html`
<div class="row space-top">
    <div class="col-md-12">
        ${userpage 
            ? html`
                <h1>My Furniture</h1>
                <p>This is a list of your publications.</p>`
            : html`
                <h1>Welcome to Furniture System</h1>
                <p>Select furniture from the catalog to view details.</p>`}        
    </div>
    <div class="col-md-12">
        <form @submit=${onSearch}>
            <input type="text" name="search" .value=${search}>
            <input type="submit" value="Search">
        </form>
    </div>
</div>
<div class="row space-top">
    <!--
    <a class="page-index btn btn-info" href="?page=1">1</a>
    <a class="page-index btn btn-info" href="?page=2">2</a>
    <a class="page-index btn btn-info" href="?page=3">3</a>-->
    ${until(dataPromise, html`<p>Loading... </p>`)}
</div>`;

// ${currPage > 1 ? html`<a class="page-index btn btn-info" href=${`?page=${currPage - 1}` + }> &lt; Prev</a>` : null}
// ${currPage < totalPages ? html`<a class="page-index btn btn-info" href=${`?page=${currPage + 1}`}>Next &gt;</a>` : null}

const pagerTemplate = (currPage, totalPages, search) => html`
<div>
    ${currPage > 1 ? html`<a class="page-index btn btn-info" href=${createPageHref(currPage, -1, search)}> &lt; Prev</a>` : null}
    ${currPage < totalPages ? html`<a class="page-index btn btn-info" href=${createPageHref(currPage, +1, search)}>Next &gt;</a>` : null}
</div>`;

const itemTemplate = (item) => html`
<div class="col-md-4">
    <div class="card text-white bg-primary">
        <div class="card-body">
            <img src=${item.img} />
            <p>${item.description}</p>
            <footer>
                <p>Price: <span>${item.price} $</span></p>
            </footer>
            <div>
                <a href=${`/details/${item._id}`} class="btn btn-info">Details</a>
            </div>
        </div>
    </div>
</div>`;

function createPageHref(currPage, step, search) {
    return`?page=${currPage + step}` + (search ? `&search=${search}` : '');
}

export function catalogPage(ctx) {
    console.log(ctx);
    const query = parseQueryString(ctx.querystring);
    console.log("query", query);
    const page = Number(query.page || 1);
    console.log("s", query.search);
    const search = query.search || '';


    const userpage = ctx.pathname == '/my-furniture';
    ctx.renderProp(catalogTemplate(loadItems(userpage, page, search), userpage, onSearch, search));

    function onSearch(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const searchParam = formData.get('search').trim();

        if (searchParam) { //ако има searchParam
            console.log("ss", search);
            ctx.page.redirect(`?search=${searchParam}`);
        } else {
            ctx.page.redirect(`/`);
        }
        
        // ctx.page.redirect(`?search=${searchParam}`);
    }
}

async function loadItems(userpage, page, search) {
    let items = [];
    if (userpage) {
        const userId = getUserData().id;
        items = await getMyItems(userId, page, search); //тук не бачка все още
    } else {
        items = await getAll(page, search); //масив от записи от сървъра
    }

    return [
        pagerTemplate(page, items.totalPages, search),
        ...items.data.map(itemTemplate) //масив от рендирани темплейти за всеки item ги правим на поредица от данни заедно за html-a да е един цял        
    ];
}
