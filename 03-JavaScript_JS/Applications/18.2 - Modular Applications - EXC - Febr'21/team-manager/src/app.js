import * as api from './api/data.js';
import {page, render} from './lib.js';
import { getUserData } from './util.js';
import { browsePage } from './views/browse.js';
import { createTeamPage } from './views/createTeam.js';
import { detailsTeamPage } from './views/detailsTeam.js';
import { editTeamPage } from './views/editTeam.js';
import { homePage } from './views/home.js';
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';

// window.api = api;
const main = document.querySelector('main');
document.getElementById('logoutBtn').addEventListener('click', onLogout);

page('/', decorateContext, homePage);
page('/index.html', decorateContext, homePage);
page('/browse', decorateContext, browsePage);
page('/login', decorateContext, loginPage);
page('/register', decorateContext, registerPage);
page('/create', decorateContext, createTeamPage);
page('/details/:id', decorateContext, detailsTeamPage);
page('/edit/:id', decorateContext, editTeamPage);
setUserNav();
page.start();

function decorateContext(ctx, next) {
    ctx.setUserNavProp = setUserNav;
    ctx.renderProp = (content) => render(content, main);

    next();
}

function setUserNav() {
    const userData = getUserData();

    if (userData) {
        [...document.querySelectorAll('nav > a.user')].forEach(a => a.style.display = 'block'); 
        [...document.querySelectorAll('nav > a.guest')].forEach(a => a.style.display = 'none'); 
    } else {
        [...document.querySelectorAll('nav > a.user')].forEach(a => a.style.display = 'none'); 
        [...document.querySelectorAll('nav > a.guest')].forEach(a => a.style.display = 'block'); 
    }
}

async function onLogout(){
    await api.logout();
    setUserNav();
    page.redirect('/');
}