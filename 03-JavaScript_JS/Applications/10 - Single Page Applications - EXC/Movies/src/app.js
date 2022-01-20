import { showHome } from "./home.js";
import { showLogin } from "./login.js";
import { showRegister } from "./register.js";

//create placeholder modules for every view
const nav = document.querySelector("nav");

document.getElementById('logoutBtn').addEventListener('click', onLogout);
nav.addEventListener('click', (event) => {
    if (event.target.tagName == 'A') { //anchor от HTML-a
        const view = views[event.target.id];
        if (typeof view == 'function') {
            event.preventDefault();
            view();
        }
    }
});



updateNav();
//start application in home view (catalog)
showHome();

//configure and test navigation
const views = {
    'homeLink': showHome,
    'loginLink': showLogin,
    'registerLink': showRegister,
};

export function updateNav() {
    const userData = JSON.parse(sessionStorage.getItem('userData'));
    if (userData != null) {
        nav.querySelector('#welcomeMsg').textContent = `Welcome, ${userData.email}`;
        [...nav.querySelectorAll('.user')].forEach(d => d.style.display = 'block');
        [...nav.querySelectorAll('.guest')].forEach(d => d.style.display = 'none');
    } else {
        [...nav.querySelectorAll('.user')].forEach(d => d.style.display = 'none');
        [...nav.querySelectorAll('.guest')].forEach(d => d.style.display = 'block');
    }
}

async function onLogout(event) {
    event.preventDefault();
    event.stopImmediatePropagation();
    const {token} = JSON.parse(sessionStorage.getItem('userData'));

    //get request
    await fetch('http://localhost:3030/users/logout', {
        headers: {
            'X-Authorization': token
        }
    });

    sessionStorage.removeItem('userData');
    updateNav();
    showLogin();
}


//implement modules
//-- create async functions for requests
//-- implement DOM logic

// Order of views:
//x catalog (home view)
//x login
//x logout
//- register - едно if допълнително има спрямо login
//- create film
//x details  //x likes
//- edit film
//- delete film

