// import { render } from 'https://unpkg.com/lit-html?module';
import { onUntil } from './delayed.js';
import { html, render } from './node_modules/lit-html/lit-html.js';

import articleTemplate from './templates/articles.js';

start();

async function start() {
    const data = await (await fetch('./data.json')).json();//това го правихме на 2 реда, сега го правим на един ред    
    const main = document.querySelector('main');
    const renderBtn = document.getElementById('renderBtn');
    renderBtn.addEventListener('click', onRender);
    
    document.getElementById('changeBtn').addEventListener('click', onChange);
    document.getElementById('untilBtn').addEventListener('click', onUntil);

    function onRender(ev) {
        data[0].author += '1';
        const result = data.map(artl => articleTemplate(onSubmit.bind(null, artl), artl)); //на всеки article подаваме като първи параметър bind-ната функция към него

        render(result, main);
    }

    function onChange(ev) {
    }

    function onSubmit(article, event) {
        event.preventDefault();
        
        const formData = new FormData(event.target);
        const content = formData.get('comment');

        article.comments.push({content});

        onRender();
    }
}








