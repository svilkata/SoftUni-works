import { html, render as litRender } from '../node_modules/lit-html/lit-html.js';

const cardTemplate = ({ avatar, infoDisplay }, onToggle) => html`
<style>
    .user-card {
        display: flex;
        font-family: 'Arial', sans-serif;
        background-color: #EEE;
        border-bottom: 5px solid darkorchid;
        width: 100%;
    }

    .user-card img {
        width: 200px;
        height: 200px;
        border: 1px solid darkorchid;
    }

    .info {
        display: flex;
        flex-direction: column;
    }

    .info h3 {
        font-weight: bold;
        margin-top: 1em;
        text-align: center;
    }

    .info button {
        outline: none;
        border: none;
        cursor: pointer;
        background-color: darkorchid;
        color: white;
        padding: 0.5em 1em;
    }

    @media only screen and (max-width: 500px) {
        .user-card {
            flex-direction: column;
            margin-bottom: 1em;
        }

        .user-card figure,
        .info button {
            align-self: center;
        }

        .info button {
            margin-bottom: 1em;
        }

        .info p {
            padding-left: 1em;
        }
    }
</style>

<div class="user-card">
    <figure>
        <img src="./images/${avatar}" />
    </figure>
    <div class="info">
        <h3><slot name="usernames" /></h3>

        <div style="display: ${infoDisplay ? 'block' : 'none'}">
            <p>
                <slot name="email" />
            </p>
            <p>
                <slot name="phone" />
            </p>
        </div>

        <button class="toggle-info-btn" @click="${onToggle}">${infoDisplay ? 'Hide info' : 'Show info'}</button>
    </div>
</div>`;

class UserCard extends HTMLElement {
    constructor() {
        super();
        this.attachShadow({ mode: 'open' });

        this.state = {
            avatar: this.getAttribute('avatar'),
            infoDisplay: false,
        };
    }

    connectedCallback() {
        this.render();
    }

    // https://lit.dev/docs/v1/lit-html/writing-templates/
    // Event listener objects. When you specify a listener using an event listener object, 
    // the listener object itself is set as the event context (this value).
    // this винаги сочи към обекта, който сме подали
    // Тъпото е, че можем да имаме само един такъв EventListener,  но пък можем да го bind-ваме просто ако са ни нужни повече
    handleEvent() {
        console.log('clicked');
        this.state.infoDisplay = !this.state.infoDisplay;
        this.render();
    }

    render() {
        litRender(
            cardTemplate(this.state, this),
            this.shadowRoot,
        );
    }
}

customElements.define('user-card', UserCard);