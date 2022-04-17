import { register } from "../api/data.js";
import { html } from "../lib.js";

const registerTemplate = (onSubmit) => html`
<!-- Register Page (Only for Guest users) -->
<section id="register-page" class="auth">
    <form @submit=${onSubmit} id="register">
        <h1 class="title">Register</h1>

        <article class="input-group">
            <label for="register-email">Email: </label>
            <input type="email" id="register-email" name="email">
        </article>

        <article class="input-group">
            <label for="register-password">Password: </label>
            <input type="password" id="register-password" name="password">
        </article>

        <article class="input-group">
            <label for="repeat-password">Repeat Password: </label>
            <input type="password" id="repeat-password" name="repeatPassword">
        </article>

        <input type="submit" class="btn submit-btn" value="Register">
    </form>
</section>`;

export function registerPage(ctx) {
    ctx.renderProp(registerTemplate(onSubmit));

    //event listener-а няма опасност да бъде добавен два пъти
    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);

        const email = formData.get('email').trim();
        const password = formData.get('password').trim();
        const rePass = formData.get('repeatPassword').trim();

        if (email == '' || password == '' || rePass == '') {
            return alert('All fields are required!');
        }

        if (password != rePass) {
            return alert('Passwords don"t match!');
        }

        await register(email, password);
        ctx.updateUserNav();  //тук няма нужда все още да сменяме nav бутоните
        ctx.page.redirect('/');
    }
}
