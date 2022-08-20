import { html } from '../lib.js';
import { getAllPosts } from '../api/data.js';

//Първо си правим един темплейт статичен, и след това изкарваме от него втори темплейт за всеки филм. При което става динамичен templating.
const dashboardFullTemplate = (dashPosts) => html`
<section id="dashboard-page">
    <h1 class="title">All Posts</h1>

    <div class="all-posts">
        ${dashPosts.length == 0 
            ? html`<h1 class="title no-posts-title">No posts yet!</h1>`
            : dashPosts.map(dashCard)}
    </div>    
</section>`;

const dashCard = (aPost) => html`
<div class="post">
    <h2 class="post-title">${aPost.title}</h2>
    <img class="post-image" src=${aPost.imageUrl} alt="Material Image">
    <div class="btn-wrapper">
        <a href=${`/details/${aPost._id}`} class="details-btn btn">Details</a>
    </div>
</div>`;


export async function dashboardPage(ctx) {
    const dashPosts = await getAllPosts(); //масив от постове, resolve-нат!!!  
    
    ctx.renderProp(dashboardFullTemplate(dashPosts));
}






