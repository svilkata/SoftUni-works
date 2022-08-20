import { searchToFindItems } from '../api/data.js';
import { html } from '../lib.js';
import { getUserData } from '../utils.js';

//Първо си правим един темплейт статичен, и след това изкарваме от него втори темплейт за всеки филм. При което става динамичен templating.
const searchTemplate = (onSearch, itemsShoes, params = '', userData) => html`
<!-- Search Page (Only for logged-in users) -->
<section id="search">
    <h2>Search by Brand</h2>

    <form @submit=${onSearch} class="search-wrapper cf">
        <input id="#search-input" type="text" name="search" placeholder="Search here..." .value=${params} required/>
        <button type="submit">Search</button>
    </form>

    <h3>Results:</h3>

    <div id="search-container">
        <ul class="card-wrapper">
            ${params && itemsShoes.length == 0 
                ? html`<h2>There are no results found.</h2>`
                : itemsShoes.map((itm) => shoeCard(itm, userData))}
        </ul>    
    </div>
</section>`;

const shoeCard = (itm, userData) => html`
<li class="card">
    <img src=${itm.imageUrl} alt="eminem" />
    <p><strong>Brand: </strong><span class="brand">${itm.brand}</span></p>
    <p><strong>Model: </strong><span class="model">${itm.model}</span></p>
    <p><strong>Value:</strong><span class="value">${itm.value}</span>$</p>
    ${
        userData
            ? html`<a class="details-btn" href=${`/detailsitem/${itm._id}`}>Details</a>`
            : null
    }    
</li>`;


export async function searchPage(ctx) {
    const userData = getUserData();
    const params = ctx.querystring.split('=')[1];
    let itemsShoes = [];

    if (params) {
        itemsShoes = await searchToFindItems(params); //масив от постове, resolve-нат!!!
    }

    ctx.renderProp(searchTemplate(onSearch, itemsShoes, params, userData));

    function onSearch(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const search = formData.get('search');

        if (search != '') {
            ctx.page.redirect('search?query=' + encodeURIComponent(search));     
            console.log(search);
        }
    }

    
  
    
    // ctx.renderProp(profileTemplate(myPosts));
}






