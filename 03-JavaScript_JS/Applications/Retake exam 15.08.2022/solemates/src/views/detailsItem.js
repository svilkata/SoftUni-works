import { html } from '../lib.js';
import { getAnItemById, deleteItemById } from '../api/data.js';
import { getUserData } from '../utils.js';

const detailsPostTemplate = (item, userData, onDelete) => html`
<!-- Details page -->
<section id="details">
    <div id="details-wrapper">
        <p id="details-title">Shoe Details</p>
        <div id="img-wrapper">
            <img src=${item.imageUrl} alt="example1" />
        </div>
        <div id="info-wrapper">
            <p>Brand: <span id="details-brand">${item.brand}</span></p>
            <p>Model: <span id="details-model">${item.model}</span></p>
            <p>Release date: <span id="details-release">${item.release}</span></p>
            <p>Designer: <span id="details-designer">${item.designer}</span></p>
            <p>Value: <span id="details-value">${item.value}</span></p>
        </div>

        <!--Edit and Delete are only for creator-->
        <div id="action-buttons">
            ${(userData && item._ownerId == userData.id)
                ? html`
                    <a href="/edititem/${item._id}" id="edit-btn">Edit</a>
                    <button @click=${onDelete} id="delete-btn">Delete</button>`
                : null}       
        </div>
    </div>
</section>`;

export async function detailsItemPage(ctx) {
    const itemId = ctx.params.id;
    const item = await getAnItemById(itemId);

    const userData = getUserData();
    // const isOwner = userData && post._ownerId == userData.id;

    ctx.renderProp(detailsPostTemplate(item, userData, onDelete));

    async function onDelete() {
        const choice = confirm("Are you sure you want to delete this post forever?");

        //if true
        if (choice) {
            await deleteItemById(itemId);
            ctx.page.redirect("/dashboard");
        }
    }
}

