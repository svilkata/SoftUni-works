import { showCatalogPage } from "./catalog.js";
import { showHomePage, showAboutPage } from "./home.js";
import { showLoginPage } from "./login.js";
import { showRegisterPage } from "./register.js";

// const main = document.querySelector('main');
document.getElementById('logoutBtn').addEventListener('click', onLogout);
document.querySelector('nav').addEventListener('click', onNavigate);

//start application in home view
showHomePage();

const sections = {
    'homeBtn': showHomePage,
    'catalogBtn':showCatalogPage,
    'aboutBtn':showAboutPage,
    'loginBtn': showLoginPage,
    'registerBtn': showRegisterPage
}

updateUserNav();

function onNavigate(event) {
    console.log("Inside onNavigate");

    if (event.target.tagName == 'A') {//anchor //BUTTON - button
        const view = sections[event.target.id];

        console.log(view);

        if (typeof view == 'function') {
            event.preventDefault(); //не отивай към друга html страница
            view();
        }             
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
    const {token} = JSON.parse(sessionStorage.getItem('userData'));

    await fetch('http://localhost:3030/users/logout', {
        headers: {
            'X-Authorization': token
        }
    });

    sessionStorage.removeItem('userData');
    updateUserNav();
    showHomePage();   
}

