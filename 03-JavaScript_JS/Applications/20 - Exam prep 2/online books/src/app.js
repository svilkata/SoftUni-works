import * as apiData from './api/data.js'
import { page, render } from './lib.js';
import { homePage } from './views/home.js';
import { loginPage } from './views/login.js';
import { getUserData } from './util.js';
import { registerPage } from './views/register.js';
import { createBookPage } from './views/createBookScreen.js';
import { detailsBookPage } from './views/detailsBook.js';
import { editBookPage } from './views/editBookScreen.js';
import { myBooksPage } from './views/my-books.js';
import { searchPage } from './views/search.js';

//debug
// window.apiData = apiData;

const root = document.getElementById('site-content');
document.getElementById('logoutBtn').addEventListener('click', onLogout);

page(decorateContext);
page('/', homePage);
page('/login', loginPage);
page('/register', registerPage);
page('/create', createBookPage);
page('/details/:id', detailsBookPage);
page('/edit/:id', editBookPage);
page('/my-books', myBooksPage);
page('/search', searchPage);



updateUserNav();
page.start();

function decorateContext(ctx, next) {
    ctx.renderProps = (content) => render(content, root);
    ctx.updateUserNav = updateUserNav;

    next();
}

function updateUserNav() {
    const userData = getUserData();
    if (userData) {
        document.getElementById('user').style.display = 'inline-block';
        document.getElementById('guest').style.display = 'none';
        document.querySelector('#user span').textContent = `Welcome, ${userData.email}`;  //се изписва като логнат потребител
    } else {
        document.getElementById('user').style.display = 'none';
        document.getElementById('guest').style.display = 'inline-block';
    }
}


async function onLogout() {
    await apiData.logout();
    updateUserNav();// в същия scope
    page.redirect('/');
}
