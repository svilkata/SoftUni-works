//абстрактен модул, който ще управлява заявките
import { logout } from './api/data.js';
import { showCatalogPage } from './views/catalog.js';
import { showCreatePage } from './views/create.js';
import { showDetailsPage } from './views/details.js';
import { showHomePage } from './views/home.js';
import { showLoginPage } from './views/login.js';
import { showRegisterPage } from './views/register.js';
import { showSection } from './dom.js';

// window.api = api;

//модул, който превключва изгледите/views
const links = {
    'homeLink': 'home',
    'catalogLink': 'catalog',
    'loginLink': 'login',
    'registerLink': 'register',
    'createLink': 'create'
}

const views = {
    'home': showHomePage,
    'catalog': showCatalogPage,
    'login': showLoginPage,
    'register': showRegisterPage,
    'create': showCreatePage,
    'details': showDetailsPage
}

document.getElementById('logoutBtn').addEventListener('click', onLogout);
const nav = document.querySelector('nav');
nav.addEventListener('click', onNavigate);

const ctx = {
    goTo,
    showSection,
    updateNav
}

//Start application in how view
updateNav();
goTo('home');

function onNavigate(event) {
    console.log(event.target.id);
    const name = links[event.target.id];
    console.log(name);
    if (name) {
        event.preventDefault();
        goTo(name);
    }
}

function goTo(name, ...params) {
    console.log("I am on the goTo function now");
    const view = views[name];
    if (typeof view == 'function') {
        view(ctx, ...params);//
    }
}

function updateNav() {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    if (userData != null) {
        [...nav.querySelectorAll('.user')].forEach(l => l.style.display = 'block');
        [...nav.querySelectorAll('.guest')].forEach(l => l.style.display = 'none');
    } else {
        [...nav.querySelectorAll('.user')].forEach(l => l.style.display = 'none');
        [...nav.querySelectorAll('.guest')].forEach(l => l.style.display = 'block');
    }
}

async function onLogout(event) {
    event.stopImmediatePropagation(); //друг event listener (onNavigate) да не се изпълнява върху същия anchor/button
    console.log("Inside onLogout");

    //from ./api/data.js
    await logout();

    updateNav();
    goTo('home');
}
