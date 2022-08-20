import { html } from '../lib.js';
import { addTheNewItem } from '../api/data.js';

//Първо си правим един темплейт статичен, и след това изкарваме от него втори темплейт за всеки филм. При което става динамичен templating.
const createAddNewPairTemplate = (onSubmit) => html`
<!-- Create Page (Only for logged-in users) -->
<section id="create">
    <div class="form">
        <h2>Add item</h2>
        <form @submit=${onSubmit} class="create-form">
            <input type="text" name="brand" id="shoe-brand" placeholder="Brand" />
            <input type="text" name="model" id="shoe-model" placeholder="Model" />
            <input type="text" name="imageUrl" id="shoe-img" placeholder="Image url" />
            <input type="text" name="release" id="shoe-release" placeholder="Release date" />
            <input type="text" name="designer" id="shoe-designer" placeholder="Designer" />
            <input type="text" name="value" id="shoe-value" placeholder="Value" />

            <button type="submit">post</button>
        </form>
    </div>
</section>`;


export async function addNewItemPage(ctx) {
    ctx.renderProp(createAddNewPairTemplate(onSubmit));

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

        await addTheNewItem({
            brand,
            model,
            imageUrl,
            release,
            designer,
            value
        });

        ctx.page.redirect('/dashboard');
    }
}






