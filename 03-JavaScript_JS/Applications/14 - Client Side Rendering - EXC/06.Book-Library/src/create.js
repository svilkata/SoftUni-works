import {createBook, html} from './utility.js';

//create module:
// control create form

//onSuccess callback
const createTemplate = (onSuccess) => html`
<form @submit=${ev => onSubmit(ev, onSuccess)} id="add-form">
    <h3>Add book</h3>
    <label>TITLE</label>
    <input type="text" name="title" placeholder="Title...">
    <label>AUTHOR</label>
    <input type="text" name="author" placeholder="Author...">
    <input type="submit" value="Submit">
</form>`;

export function showCreate(ctx){
    //подаваме контекста ctx.updateRender() като callback, и контекста ще стигне до submit-a на createTemplate
    if (ctx.book != undefined) {
        return null;
    } else {
        return createTemplate(ctx.updateRender);
    }    
}

//onSuccess е callback
async function onSubmit(event, onSuccess) {
    event.preventDefault();
    const formData = new FormData(event.target);
    console.log(event.target);

    const title = formData.get('title').trim();
    const author = formData.get('author').trim();

    await createBook({author, title});

    event.target.reset();
    onSuccess();//изпълняване на callback функцията
}