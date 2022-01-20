import { html, render } from './node_modules/lit-html/lit-html.js';

const selectTemplate = (items) => html`
<select id="menu">
    ${items.map(i => html`<option value=${i._id}>${i.text}</option>`)}
</select>
`;

//start:
// add event listener
// call getData
const url = 'http://localhost:3030/jsonstore/advanced/dropdown';
const root = document.querySelector('div');
document.querySelector('form').addEventListener('submit', addItem);
getData();

//getData:
// fetch and parse
// call update
async function getData(params) {
    const response = await fetch(url);
    const data = await response.json();

    update(Object.values(data)); //дропва ключовете
}

//update:
// render template
function update(items) {
    const result = selectTemplate(items);
    render(result, root);
}

//add item:
// read input
// make POST request
// on success, call getData
// асинхронна е, защото искаме да продължим чак когато post заявката е била успешна
async function addItem(event) {
    event.preventDefault();
    const text = document.getElementById('itemText').value;

    const response = await fetch(url, {
        method: 'post',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({text})
    });

    if (response.ok == true) {
        getData();
    }
}