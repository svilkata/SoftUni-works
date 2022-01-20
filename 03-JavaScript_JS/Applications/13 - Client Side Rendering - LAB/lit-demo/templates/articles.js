import { html } from '../node_modules/lit-html/lit-html.js';
import commentTemplate from './comment.js';

const articleTemplate = (onSubmit, dataEl) =>
    html`
<article>
    <input type="text" ?disabled=${dataEl.disabled} .value=${dataEl.color}>
    <h3>${dataEl.title}</h3>
    <div class="${dataEl.color}">
        <p>${dataEl.content}</p>
    </div>
    <footer>Author: ${dataEl.author}</footer>
    <div class="comments">
        <form @submit=${onSubmit}>
            <textarea name="comment"></textarea>
            <button>Submit comment</button>
        </form>

        <ul>
            ${dataEl.comments.map(commentTemplate)}
        </ul>        
    </div>
</article>`;

export default articleTemplate;