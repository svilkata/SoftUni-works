import { updateUserNav } from "./app.js";
import { showSection} from "./dom.js";
import { showHomePage } from "./home.js";

const registerSection = document.getElementById('registerSection');
registerSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването
const form = registerSection.querySelector('form'); //формулярът от нашата секция
form.addEventListener('submit', onSubmit);
//кодът по-горе се изпълява само веднъж при стартиране на браузъра, и няма как да се изпълни два пъти

export function showRegisterPage() {
    showSection(registerSection);
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

    try {
        const response = await fetch('http://localhost:3030/users/register', {
            method: 'post',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });
        
        if (response.ok != true) {
            const error = await response.json();
            throw new Error(error.message);
        }        

        const data = await response.json();
        const userData = {
            username: data.username,
            id: data._id,
            token: data.accessToken
        }
        sessionStorage.setItem('userData', JSON.stringify(userData));

        updateUserNav();
        showHomePage();
    } catch (err) {
        alert(err.message);
    }
}