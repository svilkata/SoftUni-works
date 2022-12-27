import { html, render as litRender } from './node_modules/lit-html/lit-html.js';

const articleTemplate = (title, content, onClick, color) => html`
<style>
    article {
        margin: 32px;
        background-color: white;
        box-shadow: 0 0 8px 16px rgba(0,0,0,0.2);
    }

    article h2 {
        background-color: #ccc;
        padding: 8px 16px;
    }

    article div {
        padding: 16px;
        color: ${color};
    }
</style>
<article>
    <h2>${title} <button @click=${onClick}>Change color</button></h2>
    <div>
        <p>${content}</p>
    </div>
</article>`;

class MyArticle extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: 'open' });


        this._state = {
            color: 'black'
        };
        Object.defineProperty(this, 'state', {
            get() { return this._state; },
            set(value) {
                this._state = value;
                this.render();
            }
        });
        // this.state = {
        //     color: 'black'
        // };

        this.colorQueue = ['red', 'green', 'blue'];
    }

    connectedCallback() {
        this.render();
    }

    changeColor() {
        // this.state.color = this.colorQueue.shift();
        // this.colorQueue.push(this.state.color);

        this._state = { color: this.colorQueue.shift() };
        this.colorQueue.push(this._state.color);
        this.render();
    }

    render() {
        litRender(
            // articleTemplate('Title', 'Content', this.changeColor.bind(this), this.state.color),
            articleTemplate('Title', 'Content', this.changeColor.bind(this), this._state.color),
            this.shadowRoot,
            { eventContext: this });
    }
}

customElements.define('my-article', MyArticle);











