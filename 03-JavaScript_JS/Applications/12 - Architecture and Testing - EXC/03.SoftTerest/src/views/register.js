const section = document.getElementById('registerPage');
section.remove();

const form = section.querySelector('form');
form.addEventListener('submit', onSubmit);
let ctx = null;

export async function showRegisterPage(ctxTarget) {
    ctx = ctxTarget;
    ctx.showSection(section);
}

async function onSubmit(event) {
    event.preventDefault();
    const formData = new FormData(form);

    const email = formData.get('email').trim();
    const password = formData.get('password').trim();
    const repass = formData.get('repeatPassword').trim();

    if (!email || !password) {
        return alert("All fields are required");
    }

    if (password != repass) {
        alert('Passwords do not match');
        return;
    }

    await register(email, password);
    ctx.updateNav();
    form.reset();
    ctx.goTo('home');
}