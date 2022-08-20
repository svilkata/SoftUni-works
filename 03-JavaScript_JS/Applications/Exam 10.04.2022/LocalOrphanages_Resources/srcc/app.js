import { logout } from "./api/data.js";
import { page, render } from './lib.js';
import { getUserData } from './utils.js';

import { dashboardPage } from './views/dashboard.js';
import { loginPage } from './views/login.js';
import { registerPage } from './views/register.js';
import { createPostPage } from './views/createPost.js';
import { detailsPostPage } from './views/detailsPost.js';
import { editPostPage } from './views/editPost.js';
import { profilePage } from "./views/profile.js";

const root = document.querySelector('main');
document.getElementById('logoutBtn').addEventListener('click', onLogout);

/*debug*/
// import * as api from './api/data.js';
// window.api = api;

page(decorateContext); //ще се изпълни винаги /ще се закачи винаги //глобален middle-ware
//вместо да викаме всеки път decorateContext функцията като chain-ване  
// page('/home', decorateContext, catalogPage);//Chain-ваме route handlers - така наречения middle-ware pattern, то от Page.js са измислили да се декларира в началото и без първи параметър

page('/', dashboardPage);
page('/register', registerPage);
page('/login', loginPage);
page('/createPost', createPostPage);
page('/details/:id', detailsPostPage);
page('/edit/:id', editPostPage);
page('/browseMyPosts', profilePage);


page('/', '/home');

updateUserNav();
page.start(); //Закачане на eventListener-ите

//next is callback
//цялата тази функция decorateContext се нарича middle-ware
async function decorateContext(ctx, next) {
    ctx.renderProp = (template) => render(template, root);   //задай пропърти renderProp
    ctx.updateUserNav = updateUserNav;   //задай пропърти updateUserNav за прилагане в други scope-ове
    next();//аз приключих, всичко е наред, можеш да извикаш следващия
}

function updateUserNav() {
    const userData = getUserData();
    if (userData) {
        [...document.querySelectorAll('nav div#guest a')].forEach(e => e.style.display = 'none');
        [...document.querySelectorAll('nav div#user a')].forEach(e => e.style.display = 'inline-block');
        // document.getElementById('welcomeMsg').textContent = `Welcome, ${userData.email}`;
    } else {
        [...document.querySelectorAll('nav div#guest a')].forEach(e => e.style.display = 'inline-block');
        [...document.querySelectorAll('nav div#user a')].forEach(e => e.style.display = 'none');
    }
}

//няма нужда тук да викаме preventDefault на event-a, защото в html-a имаме
//<a id="logoutBtn" href="javascript:void(0)">Logout</a>
//тази функция можем да я извикаме и с addEventListener
async function onLogout() {
    await logout();
    updateUserNav();// в същия scope
    page.redirect('/');
}

//При logout, по-добре да не използваме тук async/await
//нас не ни интересува какво ще отговори сървъра, важното е да изчистим при нас сесията. Затова не правим async
//защото сървъра ако даде грешка, няма да се изпълни updateUserNav
// function onLogout() {
//     logout();
//     updateUserNav();// в същия scope
//     page.redirect('/');
// }
