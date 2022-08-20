import { html } from '../lib.js';
import { getCurrentlyLoggedInUserPosts } from '../api/data.js';
import { getUserData } from '../utils.js';

//Първо си правим един темплейт статичен, и след това изкарваме от него втори темплейт за всеки филм. При което става динамичен templating.
const profileTemplate = (myPosts) => html`
<section id="my-posts-page">
    <h1 class="title">My Posts</h1>

    <div class="my-posts">
        ${myPosts.length == 0 
            ? html`<h1 class="title no-posts-title">You have no posts yet!</h1>`
            : myPosts.map(postCard)}
    </div>    
</section>`;

const postCard = (aPost) => html`
<div class="post">
    <h2 class="post-title">${aPost.title}</h2>
    <img class="post-image" src=${aPost.imageUrl} alt="Material Image">
    <div class="btn-wrapper">
        <a href=${`/details/${aPost._id}`} class="details-btn btn">Details</a>
    </div>
</div>`;

export async function profilePage(ctx) {
    const userData = getUserData();

    const myPosts = await getCurrentlyLoggedInUserPosts(userData.id); //масив от постове, resolve-нат!!!  
    
    ctx.renderProp(profileTemplate(myPosts));
}






