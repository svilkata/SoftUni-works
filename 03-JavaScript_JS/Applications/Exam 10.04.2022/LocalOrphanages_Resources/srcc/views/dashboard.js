import { html, until } from '../lib.js';
import { getAllPosts } from '../api/data.js';

//Първо си правим един темплейт статичен, и след това изкарваме от него втори темплейт за всеки филм. При което става динамичен templating.
const dashboardFullTemplate = (postsPromise) => html`
<section id="dashboard-page">
    <h1 class="title">All Posts</h1>

    <div class="all-posts">
        ${until(postsPromise, html`<p>Loading&hellip; </p>`)}
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


export function dashboardPage(ctx) {   
    ctx.renderProp(dashboardFullTemplate(loadPosts()));
}

async function loadPosts() {
    const dashPosts = await getAllPosts(); //масив от постове, resolve-нат!!!
    if (dashPosts.length == 0) {
        return html`<h1 class="title no-posts-title">No posts yet!</h1>`;
    } else {
        return dashPosts.map(dashCard); //масив от рендирани темплейти за всеки пост 
    }    
}






