import { register } from "../api/data.js";
import { html } from "../lib.js";

const registerTemplate = (onSubmit) => html`
<!-- Register Page (Only for Guest users) -->
<section id="register">
    <div class="form">
    <h2>Register</h2>
    <form @submit=${onSubmit} class="login-form">
        <input type="text" name="email" id="register-email" placeholder="email" />
        <input type="password" name="password" id="register-password" placeholder="password" />
        <input type="password" name="re-password" id="repeat-password" placeholder="repeat password" />
        <button type="submit">Register</button>
        <p class="message">Already registered? <a href="/login">Login</a></p>
    </form>
    </div>
</section>`;

export function registerPage(ctx) {
    ctx.renderProp(registerTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();
        const formData = new FormData(event.target);

        const email = formData.get('email').trim();
        const password = formData.get('password').trim();
        const passRe = formData.get('re-password').trim();

        if (email == '' || password == '' || passRe == '') {
            return alert('All fields are required!');
        }

        if (password != passRe) {
            return alert('Passwords don"t match!');
        }

        await register(email, password);
        ctx.updateUserNav();  
        ctx.page.redirect('/dashboard');
    }
}
