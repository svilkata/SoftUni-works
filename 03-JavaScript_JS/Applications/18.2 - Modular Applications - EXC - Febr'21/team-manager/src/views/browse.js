import { html, until } from '../lib.js';
// import { getUserData } from '../util.js';
import { getTeams } from '../api/data.js';
import { loaderTemplate } from './common/loader.js';

const browseTemplate = (teams) => html`
<section id="browse">

    <article class="pad-med">
        <h1>Team Browser</h1>
    </article>

    <article class="layout narrow">
        <div class="pad-small"><a href="/create" class="action cta">Create Team</a></div>
    </article>

    ${teams.map(x => teamTemplate(x))}
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