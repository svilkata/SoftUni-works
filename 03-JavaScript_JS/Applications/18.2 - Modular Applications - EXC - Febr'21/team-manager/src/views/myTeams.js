import { html, until } from '../lib.js';
// import { getUserData } from '../util.js';
import { getTeams } from '../api/data.js';
import { loaderTemplate } from './common/loader.js';

const myTeamsTemplate = (teams) => html`
<section id="my-teams">

    <article class="pad-med">
        <h1>My Teams</h1>
    </article>

    <article class="layout narrow">
        <div class="pad-med">
            <p>You are not a member of any team yet.</p>
            <p><a href="#">Browse all teams</a> to join one, or use the button bellow to cerate your own
                team.</p>
        </div>
        <div class=""><a href="#" class="action cta">Create Team</a></div>
    </article>

    <article class="layout">
        <img src="./assets/rocket.png" class="team-logo left-col">
        <div class="tm-preview">
            <h2>Team Rocket</h2>
            <p>Gotta catch 'em all!</p>
            <span class="details">3 Members</span>
            <div><a href="#" class="action">See details</a></div>
        </div>
    </article>

</section>`;

const teamTemplate = (team) => html`
<article class="layout">
    <img src=${team.logoUrl} class="team-logo left-col">
    <div class="tm-preview">
        <h2>${team.name}</h2>
        <p>${team.description}</p>
        <span class="details">${team.memberCount} Members</span>
        <div><a href=${`/details/${team._id}`} class="action">See details</a></div>
    </div>
</article>`;

export async function browsePage(ctx) {
    // const userData = getUserData();
    const teams = await getTeams();
    // console.log(teams);

    ctx.renderProp(until(populateTemplate(teams), loaderTemplate()));
}

async function populateTemplate() {
    const teams = await getTeams();
    return browseTemplate(teams);
}