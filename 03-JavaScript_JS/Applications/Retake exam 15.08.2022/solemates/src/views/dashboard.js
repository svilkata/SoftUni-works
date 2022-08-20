import { html } from '../lib.js';
import { getAllShoesPosts } from '../api/data.js';

//Първо си правим един темплейт статичен, и след това изкарваме от него втори темплейт за всеки филм. При което става динамичен templating.
const dashboardFullTemplate = (dashPosts) => html`
<!-- Dashboard page -->
<section id="dashboard">
    <h2>Collectibles</h2>
    <ul class="card-wrapper">
    <!-- Display a li with information about every post (if any)-->
        ${dashPosts.length == 0 
            ? html`<h2>There are no items added yet.</h2>`
            : dashPosts.map(shoeCard)}

    </ul>    
</section>`;

const shoeCard = (aPost) => html`
<li class="card">
    <img src=${aPost.imageUrl} alt="eminem" />
    <p><strong>Brand: </strong><span class="brand">${aPost.brand}</span></p>
    <p><strong>Model: </strong><span class="model">${aPost.model}</span></p>
    <p><strong>Value:</strong><span class="value">${aPost.value}</span>$</p>
    <a class="details-btn" href=${`/detailsitem/${aPost._id}`}>Details</a>
</li>`;

export async function dashboardPage(ctx) {
    const dashPosts = await getAllShoesPosts(); //масив от shoes постове, resolve-нат!!!
    
    ctx.renderProp(dashboardFullTemplate(dashPosts));
}






