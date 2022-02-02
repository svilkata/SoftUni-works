import { getAllTopics } from '../api/data.js';
import { spinner } from '../common/spinner.js';
import { html, until } from '../lib.js';

const topicsTemplate = (topicsPromise) => html`
<h1>Topics</h1>
<div>
    ${until(topicsPromise, spinner())}
</div>`;

const topicPreviewCard = (topic) => html`
<article class="preview drop">
    <header> <a href=${`/topic/${topic._id}`}>${topic.title}</a> </header>
    <div><span>By ${topic.author.username}</span>  |  <span>23 Comments</span></div>
    <a href="#"></a>
</article>`;


export function topicsPage(ctx) {
    ctx.renderProp(topicsTemplate(loadTopics()));
}

async function loadTopics(params) {
    const topics = await getAllTopics();

    return topics.map(topicPreviewCard);
    
}