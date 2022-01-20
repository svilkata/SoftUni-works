import { login } from '../api/data.js';
import {e} from '../dom.js';

const section = document.getElementById('loginPage');
section.remove();
const form = section.querySelector('form');
form.addEventListener('submit', onSubmit);
let ctx = null;

export async function showLoginPage(ctxTarget) {
    ctx = ctxTarget;
    ctx.showSection(section);
}

async function onSubmit(event) {
    event.preventDefault();
    const formData = new FormData(form);

    const email = formData.get('email').trim();
    const password = formData.get('password').trim();

    await login(email, password);//ако мине тук, кодът надолу ще продължи да се изпълнява
    ctx.goTo('home');
    form.reset();
    ctx.updateNav();
}