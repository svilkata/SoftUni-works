import { html } from '../lib.js';

const detailsItemTemplate = (itemPost) => html`
<!-- Details Page -->
<section id="details-page">
    <h1 class="title">Post Details</h1>

    <div id="container">
        <div id="details">
            <div class="image-wrapper">
                <img src=${itemPost.imageUrl} alt="Material Image" class="post-image">
            </div>
            <div class="info">
                <h2 class="title post-title">${itemPost.title}</h2>
                <p class="post-description">${itemPost.description}</p>
                <p class="post-address">${itemPost.address}</p>
                <p class="post-number">${itemPost.phone}</p>
                <p class="donate-Item">Donate Materials: 0</p>

                <!--Edit and Delete are only for creator-->
                <div class="btns">
                    <a href="#" class="edit-btn btn">Edit</a>
                    <a href="#" class="delete-btn btn">Delete</a>

                    <!--Bonus - Only for logged-in users ( not authors )-->
                    <a href="#" class="donate-btn btn">Donate</a>
                </div>

            </div>
        </div>
    </div>
</section>`;

export function detailsItemPage(ctx) {
    debugger;
    // const itemPostId = ctx.params.id;
    ctx.renderProp(detailsItemTemplate(loadIt(ctx)));
}

function loadIt(ctx) {
    debugger;
    const thePost = ctx.loadAPost; //тук го await-ваме реално
    return thePost;
}