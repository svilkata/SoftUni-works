import * as api from '../api/data.js';//една папка нагоре

const loginSection = document.getElementById('loginSection');
loginSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването
const form = loginSection.querySelector('form'); //формулярът от нашата секция
form.addEventListener('submit', onSubmit);
//кодът по-горе се изпълява само веднъж при стартиране на браузъра, и няма как да се изпълни два пъти

let ctx = null;

export function showLoginPage(ctxTarget) {
    ctx = ctxTarget;
    ctx.showSection(loginSection);
    
}

//Имаме достъп отново през контекста да си извикаме updateUserNav и showHomePage
async function onSubmit(event) {
    event.preventDefault();
    const formData = new FormData(form);

    const email = formData.get('email').trim();
    const password = formData.get('password').trim();

    await api.login(email, password);
    ctx.updateUserNav();
    ctx.goTo('home'); //може да няма инфраструктура готова, за да го приложим
}

