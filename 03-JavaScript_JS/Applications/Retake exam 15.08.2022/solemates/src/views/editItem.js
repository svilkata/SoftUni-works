import { html } from '../lib.js';
import { getAnItemById, editTheNewItemById } from '../api/data.js';

const editItemTemplate = (item, onSubmit) => html`
<!-- Edit Page (Only for logged-in users) -->
<section id="edit">
    <div class="form">
        <h2>Edit item</h2>
        <form @submit=${onSubmit} class="edit-form">
            <input type="text" name="brand" id="shoe-brand" placeholder="Brand" .value=${item.brand} />
            <input type="text" name="model" id="shoe-model" placeholder="Model" .value=${item.model} />
            <input type="text" name="imageUrl" id="shoe-img" placeholder="Image url" .value=${item.imageUrl} />
            <input type="text" name="release" id="shoe-release" placeholder="Release date" .value=${item.release} />
            <input type="text" name="designer" id="shoe-designer" placeholder="Designer" .value=${item.designer} />
            <input type="text" name="value" id="shoe-value" placeholder="Value" .value=${item.value} />

            <button type="submit">post</button>
        </form>
    </div>
</section>`;


export async function editItemPage(ctx) {
    //we load first the item / the pairs
    const itemId = ctx.params.id;
    const item = await getAnItemById(itemId);
    
    ctx.renderProp(editItemTemplate(item, onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        
        const formData = new FormData(event.target);
        const brand = formData.get('brand');
        const model = formData.get('model');
        const imageUrl = formData.get('imageUrl');
        const release = formData.get('release');
        const designer = formData.get('designer');
        const value = formData.get('value');

        if (brand == '' || model == '' || imageUrl == '' || release == '' || designer == '' || value == '') {
            return alert('All fields are required!');
        }

        await editTheNewItemById(itemId, {
            brand,
            model,
            imageUrl,
            release,
            designer,
            value
        });
       
        ctx.page.redirect('/detailsitem/' + itemId);
    }
}






