import {html, updateBook} from './utility.js';

// update module:
// control edit form

const updateTemplate = (book, ctx) => html`
<form @submit=${ev => onSubmit(ev, ctx)} id="edit-form">
    <input type="hidden" name="id" .value=${book._id}>
    <h3>Edit book</h3>
    <label>TITLE</label>
    <input type="text" name="title" placeholder="Title..." .value=${book.title}>
    <label>AUTHOR</label>
    <input type="text" name="author" placeholder="Author..." .value=${book.author}>
    <input type="submit" value="Save">
</form>
`;

//подаваме 2 callback параметъра - един за данните за книгата, и втори контекста ctx.updateRender()и -двата ще стигнат при изпълнение submit-a на updateTemplate
export function showUpdate(ctx){
    //как да разберем дали ще показваме create (Book) или update (Book)
    if (ctx.book == undefined) {
        return null;
    } else {
        return updateTemplate(ctx.book, ctx);
    }    
}

async function onSubmit(event, ctx) {
    debugger;
    event.preventDefault();
    // console.log(event.target);
    const formData = new FormData(event.target);
    console.log(formData);

    const id = formData.get('id'); //тук не мога да разбера как FormData има достъп до id полето... наистина
    // console.log(id);
    const title = formData.get('title').trim();
    const author = formData.get('author').trim();
    
    await updateBook(id, {author, title});

    event.target.reset();
    delete ctx.book; //трием пропъртито да го няма
    ctx.updateRender();//изпълняване на callback функцията
}