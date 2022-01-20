import { contacts } from "./contacts.js";
import {html, render} from './node_modules/lit-html/lit-html.js';

const contactTemplate = (data) => html`
<div class="contact card">
    <div>
        <i class="far fa-user-circle gravatar"></i>
    </div>
    <div class="info">
        <h2>Name: ${data.name}</h2>
        <button class="detailsBtn">Details</button>
        <div class="details" id="${data.id}">
            <p>Phone number: ${data.phoneNumber}</p>
            <p>Email: ${data.email}</p>
        </div>
    </div>
</div>
`;

start();

function start() {
    const container = document.getElementById('contacts');
    container.addEventListener('click', onClick);

    onRender();

    function onClick(event) {
        if (event.target.tagName == 'BUTTON') {
            const div = event.target.parentElement.querySelector('.details');
            if (div.style.display == 'block') {
                div.style.display = 'none';
            } else {
                div.style.display = 'block';
            }            
        }        
    } 

    function onRender() {
        render(html`${contacts.map(c => contactTemplate(c))}`, container);
    }    
}