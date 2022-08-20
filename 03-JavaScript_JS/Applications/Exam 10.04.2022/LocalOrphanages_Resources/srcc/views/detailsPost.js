import { html } from '../lib.js';
import { deleteAPostById, getAPostById } from '../api/data.js';
import { getUserData } from '../utils.js';

const detailsPostTemplate = (post, userData, onDelete) => html`
<!-- Details Page -->
<section id="details-page">
    <h1 class="title">Post Details</h1>

    <div id="container">
        <div id="details">
            <div class="image-wrapper">
                <img src=${post.imageUrl} alt="Material Image" class="post-image">
            </div>
            <div class="info">
                <h2 class="title post-title">${post.title}</h2>
                <p class="post-description">Description: ${post.description}</p>
                <p class="post-address">Address: ${post.address}</p>
                <p class="post-number">Phone number: ${post.phone}</p>
                <p class="donate-Item">Donate Materials: 0</p>

                <!--Edit and Delete are only for creator-->
                <div class="btns">
                ${(userData && post._ownerId == userData.id)
                ? html`<a href="/edit/${post._id}" class="edit-btn btn">Edit</a>
                    <button @click=${onDelete} class="delete-btn btn">Delete</button>`
                :  (userData != null)
                    ? html`<a href="#" class="donate-btn btn">Donate</a>`
                    : null                    
                }
                </div>               
            </div>
        </div>
    </div>
</section>`;

export async function detailsPostPage(ctx) {
    const postId = ctx.params.id;
    const post = await getAPostById(postId);

    const userData = getUserData();
    // const isOwner = userData && post._ownerId == userData.id;

    ctx.renderProp(detailsPostTemplate(post, userData, onDelete));

    async function onDelete() {
        const choice = confirm("Are you sure you want to delete this post forever?");

        //if true
        if (choice) {
            await deleteAPostById(postId);
            ctx.page.redirect("/");
        }
    }
}

