import {html, render} from './node_modules/lit-html/lit-html.js';
import { towns as townNames } from "./towns.js";

//template:
// unsrorted list (ul)
// highlight elements based on search result
const listTemplate = (towns) => html`
<ul>
   ${towns.map(t => html`<li class=${t.match ? 'active' : ''}>${t.name}</li>`)} 
</ul>
`;

//start:
// load and parse data
// render template
// call update
// add event listener to search field
const towns = townNames.map(t => ({name: t, match: false}));
const root = document.getElementById('towns');
const input = document.getElementById('searchText');
const output = document.getElementById('result');
document.querySelector('button').addEventListener('click', onSearch);
update();

//update:
//   render template
function update() {
   render(listTemplate(towns), root);
}

//on search:
// read input value
// compare with town names and modify data
// output result found elements
// call update
function onSearch() {
   const match = input.value.trim().toLocaleLowerCase();
   let matches = 0;
   for (let t of towns) {
      if (match && t.name.toLocaleLowerCase().includes(match)) {
         t.match = true;
         matches++;
      } else {
         t.match = false;
      }      
   }

   update();

   output.textContent = `${matches} matches found`;
}
