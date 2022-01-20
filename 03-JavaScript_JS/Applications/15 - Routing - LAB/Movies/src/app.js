import { page, render } from './lib.js';
import { getUserData, loadMovie } from './util.js';
import { catalogPage } from './views/catalog.js';
import { createPage } from './views/create.js';
import { detailsPage } from './views/details.js';
import { editPage } from './views/edit.js';
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';

const root = document.querySelector('main');

page(decorateContext); //ще се изпълни винаги / ще се закачи винаги
page('/home', catalogPage);
page('/details/:id', loadMovie, detailsPage);
page('/login', loginPage);
page('/register', registerPage);
page('/create', createPage);
page('/edit/:id', loadMovie, editPage);

page('/', '/home');

updateUserNav();
// page('/home', decorateContext, catalogPage);//Chain-ваме route handlers - така наречения middle-ware pattern
page.start(); //Закачане на eventListener-ите

//next is callback
//цялата тази функция decorateContext се нарича middle-ware
async function decorateContext(ctx, next) {
    ctx.renderProp = (template) => render(template, root);
    ctx.updateUserNav = updateUserNav;
    next();//аз приключих, всичко е наред, можеш да извикаш следващия
}

function updateUserNav() {
    const userData = getUserData();
    if (userData) {
        [...document.querySelectorAll('nav .user')].forEach(e => e.style.display = 'inline-block');
        [...document.querySelectorAll('nav .guest')].forEach(e => e.style.display = 'none');
        document.getElementById('welcomeMsg').textContent = `Welcome, ${userData.email}`;
    } else {
        [...document.querySelectorAll('nav .user')].forEach(e => e.style.display = 'none');
        [...document.querySelectorAll('nav .guest')].forEach(e => e.style.display = 'inline-block');
    }
}