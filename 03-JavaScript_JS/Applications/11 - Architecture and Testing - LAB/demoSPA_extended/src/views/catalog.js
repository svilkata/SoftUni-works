import { getAllMovies } from '../api/data.js';
import { e } from "../dom.js";

const catalogSection = document.getElementById('catalogSection');
catalogSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването
const ul = catalogSection.querySelector('ul');

export function showCatalogPage(ctx) {
    ctx.showSection(catalogSection);
    loadMovies();
}

async function loadMovies() {
    ul.replaceChildren(e('p', {}, 'Loading...'));
    const movies = await getAllMovies();
    ul.replaceChildren(...movies.map(createMovieCard));
}

function createMovieCard(movie) {
    return e('li', {}, movie.title);
}