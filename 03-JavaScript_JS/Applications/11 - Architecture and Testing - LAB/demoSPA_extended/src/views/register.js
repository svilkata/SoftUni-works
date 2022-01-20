import { register } from "../api/data.js"; //една папка нагоре

const registerSection = document.getElementById('registerSection');
registerSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването
const form = registerSection.querySelector('form'); //формулярът от нашата секция
form.addEventListener('submit', onSubmit);
//кодът по-горе се изпълява само веднъж при стартиране на браузъра, и няма как да се изпълни два пъти

let ctx = null;

export function showRegisterPage(ctxTarget) {
    ctx = ctxTarget;
    ctx.showSection(registerSection);
}

async function onSubmit(event) {
    event.preventDefault();
    const formData = new FormData(form);

    const email = formData.get('email').trim();
    const password = formData.get('password').trim();
    const repass = formData.get('repass').trim();

    if (password != repass) {
        alert('Passwords do not match');
        return;
    }

    //from ./api/data.js
    await register(email, password);
    ctx.updateUserNav();
    ctx.goTo('home');
}