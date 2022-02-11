import { createTeam } from '../api/data.js';
import {html} from '../lib.js';

const createTeamTemplate = (onSubmit, errorMsg) => html`
<section id="create">
    <article class="narrow">
        <header class="pad-med">
            <h1>New Team</h1>
        </header>
        <form @submit=${onSubmit} id="create-form" class="main-form pad-large">
            ${errorMsg ? html`<div class="error">${errorMsg}</div>` : ''}   
            <label>Team name: <input type="text" name="name"></label>
            <label>Logo URL: <input type="text" name="logoUrl"></label>
            <label>Description: <textarea name="description"></textarea></label>
            <input class="action cta" type="submit" value="Create Team">
        </form>
    </article>
</section>`;

export async function createTeamPage(ctx) {
    ctx.renderProp(createTeamTemplate(onSubmit));

    async function onSubmit(event) {
        event.preventDefault();        

        const formData = new FormData(event.target);
        const name = formData.get('name');
        const logoUrl = formData.get('logoUrl');
        const description = formData.get('description');
        [...event.target.querySelectorAll('input')].forEach(i => i.disabled = true); //изключваме формулярите след като сме взели данни от formData

        try {
            if (name.length < 4) {
                throw new Error('Team name must be at least 4 characters long');
            }
            if (description.length < 10) {
                throw new Error('Description must be at least 10 characters long');
            }
            if (logoUrl == '') {
                throw new Error('Team logo is required');
            }

            const team = await createTeam({name, description, logoUrl}); //създава team и сървъра го връща съшия този team
            //TODO add creator as member and approve request
            event.target.reset();
            ctx.page.redirect('/details/' + team._id);

        } catch (err) {
            ctx.renderProp(createTeamTemplate(onSubmit, err.message));
        } finally {
            [...event.target.querySelectorAll('input')].forEach(i => i.disabled = false);
        }
    }
}
