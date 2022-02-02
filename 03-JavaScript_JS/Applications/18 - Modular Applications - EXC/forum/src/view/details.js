import { getTopicById, getCommentsByTopicId, createComment} from '../api/data.js';
import { spinner } from '../common/spinner.js';
import {html, until} from '../lib.js';
import { createSubmitHandler, getUserData } from '../util.js';

const detailsATopicTemplate = (topicPromise) => html`
<div class="drop main">
    ${until(topicPromise, spinner())}
</div>`;

const topicCard = (topic, isOwner, comments, onCommentSubmit) => html`
<header>
    <h1>${topic.title}</h1>
    <div class="controls">
        ${isOwner 
        ? html`<a class="action" href=${`/edit/${topic._id}`}>Edit</a>  <a class="action" href="javascript:void(0)">Delete</a>`
        : html`<span>Topic created by ${topic.author.username}</span>`}
    </div>    
</header>
<div class="topic-content">
<p >${topic.content}</p>
</div>

<div class="topic-content">
    ${commentForm(onCommentSubmit)}
    ${comments.map(commentCard)}
</div>`;

const commentCard = (comment) => html`
<article class="comment">
    <header>By ${comment.author.username}</header>
    <div class="topic-content">
        <p>${comment.content}</p>
    </div>    
</article>`;

const commentForm = (onCommentSubmit) => html`
<article class="comment">
    <header>Post new comment</header>   
    <div class="topic-content">
        <form @submit=${onCommentSubmit}>
            <label>Content <textarea name="content"></textarea></label>
            <input class="action" type="submit" value="Post">
        </form>
    </div>    
</article>`;

let _topicData = null;

export function detailsATopicPage(ctx) {
    _topicData = getTopicById(ctx.params.id);
    update();

    function update() {
        ctx.renderProp(detailsATopicTemplate(loadTopic(ctx.params.id, onCommentSubmit)));
    }

    async function onCommentSubmit(data, event) {
        if (data.content == '') {
            return alert('Cannot post empty comment!')
        }

        //с цел да не цъкнем два пъти на един и същи бутон
        [...event.target.querySelectorAll('input', 'textarea')].forEach(inp => inp.disabled = true);

        data.topicId = ctx.params.id;

        await createComment(data);

        [...event.target.querySelectorAll('input', 'textarea')].forEach(inp => inp.disabled = false);
        update(); //презареждаме страницата без redirect, добре би било да кешираме данните от текущата страница
    }
}

async function loadTopic(id, onCommentSubmit) {
    const [topic, comments] = await Promise.all([
        _topicData,      //кеширането тук, и реално презареждаме само коментарите, а не всичко
        getCommentsByTopicId(id)
    ]);
    
    const userData = getUserData();
    const isOwner = userData && userData.id == topic._ownerId;

    return topicCard(topic, isOwner, comments, createSubmitHandler(onCommentSubmit, 'content'));
}

