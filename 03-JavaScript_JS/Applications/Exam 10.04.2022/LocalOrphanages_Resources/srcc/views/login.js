import { html } from '../lib.js';
import { login } from '../api/data.js';

const loginTemplate = (onSubmit) => html`
<!-- Login Page (Only for Guest users) -->
<section id="login-page" class="auth">
    <form @submit=${onSubmit} id="login">
        <h1 class="title">Login</h1>

        <article class="input-group">
            <label for="login-email">Email: </label>
            <input type="email" id="login-email" name="email">
        </article>

        <article class="input-group">
            <label for="password">Password: </label>
            <input type="password" id="password" name="password">
        </article>

        <input type="submit" class="btn submit-btn" value="Log In">
    </form>
</section>`;

export function loginPage(ctx) {
    ctx.renderProp(loginTemplate(onSubmit));

    //event listener-а няма опасност да бъде добавен два пъти
    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);

        const email = formData.get('email').trim();
        const password = formData.get('password').trim();

        if (email == '' || password == '') {
            return alert('All fields are required!');
        }

        await login(email, password);
        ctx.updateUserNav();
        ctx.page.redirect('/home'); //самият контекст носи в себе си page.js библиотеката, от която можем да редиректнем.
    }
}
