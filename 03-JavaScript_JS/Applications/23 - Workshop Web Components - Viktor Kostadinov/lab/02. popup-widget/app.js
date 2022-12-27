import { html, render as litRender } from '../node_modules/lit-html/lit-html.js';

const widgetTemplate = ({text, img}) => html`
<style>
    .wrapper {
    position: relative;
    }

    .info {
    font-size: 0.8rem;
    width: 200px;
    display: inline-block;
    border: 1px solid black;
    padding: 10px;
    background: white;
    border-radius: 10px;
    opacity: 0;
    transition: 0.6s all;
    position: absolute;
    bottom: 20px;
    left: 10px;
    z-index: 3;
    }

    img {
    width: 1.2rem;
    }

    .icon:hover + .info, .icon:focus + .info {
    opacity: 1;
    }
</style>

<span class="wrapper">
    <span class="icon" tabindex="0">
    <img src="./img/${img}.png">
    </span>

    <span class="info">
    ${text}
    </span>
</span>`;

const types = {
    'info': 'info'
};

class PopupWidget extends HTMLElement {
    constructor() {
        super();

        this.attachShadow({ mode: 'open' });

        this.state = {
            img: types[this.getAttribute('type')] || 'default',
            text: this.textContent,
        };
    }

    connectedCallback() {
        litRender(
            widgetTemplate(this.state),
            this.shadowRoot,
            )
    }
}

customElements.define('popup-widget', PopupWidget);