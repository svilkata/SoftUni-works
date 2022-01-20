import { html, render } from './node_modules/lit-html/lit-html.js';

const root = document.getElementById('root');
// on submit:
// parse input
// render template
// Слагаме event listener върху формуляра, защото може като въведем данни и натиснем enter (без да кликнем бутона) 
//да генерираме събитие, което няма да може да се хване
document.querySelector('form').addEventListener('submit', (event) => {
    event.preventDefault();
    const towns = document.getElementById('towns').value.split(',').map(t => t.trim());
    
    const result = listTemplate(towns);
    render(result, root);
    
})

// template:
// ul with li for each array item

const listTemplate = (towns) => html`
<ul>
    ${towns.map(t => html`<li>${t}</li>`)}
</ul>
`;