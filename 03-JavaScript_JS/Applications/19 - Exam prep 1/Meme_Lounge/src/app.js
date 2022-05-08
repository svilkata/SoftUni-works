import { page, render } from './lib.js';
import { homePage } from './views/home.js';
import { catalogPage } from './views/catalog.js';
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';
import { getUserData } from './util.js';
import { createMemePage } from './views/createMeme.js';
import { detailsMemePage } from './views/detailsMeme.js';
import { editMemePage } from './views/editMeme.js';
import { profilePage } from './views/profile.js';


/*debug*/
import * as api from './api/data.js';
// window.api = api;

const root = document.querySelector('main');
document.getElementById('logoutBtn').addEventListener('click', onLogout);

page(decorateContext);
page('/', homePage);
page('/memes', catalogPage);
page('/login', loginPage);
page('/register', registerPage);
page('/create', createMemePage);
page('/details/:id', detailsMemePage);
page('/edit/:id', editMemePage);
page('/profile', profilePage);




updateUserNav();
page.start();

function decorateContext(ctx, next) {
    ctx.renderProps = (content) => render(content, root);
    ctx.updateUserNav = updateUserNav;
    next();
}

async function onLogout() {
    await api.logout();
    updateUserNav();// в същия scope
    page.redirect('/');
}

function updateUserNav() {
    const userData = getUserData();

    if (userData) {
        document.querySelector('.user').style.display = 'block';
        document.querySelector('.guest').style.display = 'none';
        document.querySelector('.user span').textContent = `Welcome, ${userData.email}`;
    } else {
        document.querySelector('.user').style.display = 'none';
        document.querySelector('.guest').style.display = 'block';
    }
}

