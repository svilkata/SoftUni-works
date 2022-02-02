import { logout } from './api/data.js';
import {render, page} from './lib.js';
import { getUserData } from './util.js';
import { createPage } from './view/create.js';
import { detailsATopicPage } from './view/details.js';
import { homePage } from './view/home.js';
import { loginPage } from './view/login.js';
import { registerPage } from './view/register.js';
import { topicsPage } from './view/topics.js';

const root = document.querySelector('main');
document.getElementById('logoutBtn').addEventListener('click', onLogout);

page(decorateContext);
page('/', homePage);
page('/topics', topicsPage);
page('/topic/:id', detailsATopicPage);
page('/login', loginPage);
page('/register', registerPage);
page('/create', createPage);

updateUserNav();
page.start();

function decorateContext(ctx, next) {
    ctx.renderProp = (content) => render(content, root);
    ctx.updateUserNavProp = updateUserNav;

    next();
}

function updateUserNav() {
    const userData = getUserData();

    if (userData) {
        document.querySelector('.user').style.display = 'inline-block';
        document.querySelector('.guest').style.display = 'none';
    } else {
        document.querySelector('.user').style.display = 'none';
        document.querySelector('.guest').style.display = 'inline-block';
    }
}

//нас не ни интересува какво ще отговори сървъра, важното е да изчистим при нас сесията. Затова не правим async
//защото сървъра ако даде грешка, няма да се изпълни updateUserNav
function onLogout() {
    logout();
    updateUserNav();
    page.redirect('/');
}