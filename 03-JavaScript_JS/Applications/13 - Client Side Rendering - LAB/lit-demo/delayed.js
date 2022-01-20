import {html, render} from './node_modules/lit-html/lit-html.js';
import {until} from './node_modules/lit-html/directives/until.js';

const asyncTemplate = (dataPromise) => html`
<div>
    ${until(dataPromise, html`<span>Loading&hellip;</span>`)}
</div>`;

const articleTemplate = (data) => html`
<article>
    <p>${(data).content}</p>
</article>
`;

export function onUntil() {
    const main = document.querySelector('#content');

    render(asyncTemplate(resolveTemplate(getData())), main);
}

//wrapper/опаковка
async function resolveTemplate(dataPromise) {
    const data = await dataPromise;

    return articleTemplate(data);
}

async function getData() {
    const data = {content: 'Async data'};
    return new Promise(resolve => {
        setTimeout(() => resolve(data), 2000);
    });
}