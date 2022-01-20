import { logout } from "./api/data.js";
import { showCatalogPage } from "./views/catalog.js";
import { showHomePage, showAboutPage } from "./views/home.js";
import { showLoginPage } from "./views/login.js";
import { showRegisterPage } from "./views/register.js";
import {showSection} from "./dom.js"

// const main = document.querySelector('main');
document.getElementById('logoutBtn').addEventListener('click', onLogout);
document.querySelector('nav').addEventListener('click', onNavigate);

const views = {
    'home': showHomePage,
    'catalog':showCatalogPage,
    'about':showAboutPage,
    'login': showLoginPage,
    'register': showRegisterPage
};
const links = {
    'homeBtn': 'home',
    'catalogBtn':'catalog',
    'aboutBtn': 'about',
    'loginBtn': 'login',
    'registerBtn': 'register'
};

updateUserNav();

//context = dependency injection
const ctx = {
    updateUserNav,
    goTo,
    showSection
};

//start application in home view
goTo('home');

function onNavigate(event) {
    console.log("Inside onNavigate");

    if (event.target.tagName == 'A') {//anchor //BUTTON - button
        const name = links[event.target.id];
        if (name) {
            event.preventDefault(); //не отивай към друга html страница
            goTo(name);
        }                    
    }
}

function goTo(name, ...params) {//тези параметри не са за goTo(name). Те са за другите функции, които викат goTo
    const view = views[name];
    if (typeof view == 'function') {        
        view(ctx, ...params);
    } 
}

export function updateUserNav() {
    const userData = JSON.parse(sessionStorage.getItem('userData')); 
    if (userData != null) {
        document.getElementById('userNav').style.display = 'inline-block';
        document.getElementById('guestNav').style.display = 'none';
    } else {
        document.getElementById('userNav').style.display = 'none';
        document.getElementById('guestNav').style.display = 'inline-block';
    }
}

async function onLogout(event) {
    event.stopImmediatePropagation(); //друг event listener (onNavigate) да не се изпълнява върху същия anchor/button
    console.log("Inside onLogout");

    //from ./api/data.js
    await logout();

    updateUserNav();
    goTo('home');   
}

