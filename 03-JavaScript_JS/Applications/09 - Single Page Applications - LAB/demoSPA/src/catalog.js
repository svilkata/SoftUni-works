import { showSection, e } from "./dom.js";

const catalogSection = document.getElementById('catalogSection');
catalogSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването
const ul = catalogSection.querySelector('ul');

export function showCatalogPage() {
    showSection(catalogSection);

    loadMovies();
}


async function loadMovies() {
    ul.replaceChildren(e('p', {}, 'Loading...'));

    const options = {method: 'get', headers: {}};
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    if (userData != null) {
        options.headers['X-Authorization'] = userData.token;
    }
    const response = await fetch('http://localhost:3030/data/movies', options);

    //В случай, че сървърът е рестартирал
    if (response.status == 403) {
        sessionStorage.removeItem('userData');
        updateUserNav();
        showLoginPage();
    }

    const movies = await response.json();
    ul.replaceChildren(...movies.map(createMovieCard));
}

function createMovieCard(movie) {
    return e('li', {}, movie.title);
}