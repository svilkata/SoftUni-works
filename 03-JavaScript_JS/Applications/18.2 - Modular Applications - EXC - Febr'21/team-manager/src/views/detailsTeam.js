import { getRequestsByTeamId, getTeamById, requestToJoin, cancelMembership, approveMembership } from '../api/data.js';
import { html, until } from '../lib.js';
import { getUserData } from '../util.js';
import { loaderTemplate } from './common/loader.js';

const teamDetailsTemplate = (team, isOwner, createControls, members, pending) => html`
<section id="team-home">
    <article class="layout">
        <img src=${team.logoUrl} class="team-logo left-col">
        <div class="tm-preview">
            <h2>${team.name}</h2>
            <p>${team.description}</p>
            <span class="details">${team.memberCount} Members</span>
            <div>
                ${createControls()}
            </div>
        </div>
        <div class="pad-large">
            <h3>Members</h3>
            <ul class="tm-members">
                ${members.map(memberTemplate)}
            </ul>
        </div>
        ${isOwner ? html`
        <div class="pad-large">
            <h3>Membership Requests</h3>
            <ul class="tm-members">
                ${pending.map(pendingMemberTemplate)}
            </ul>
        </div>` : ''}

    </article>
</section>`;

const memberTemplate = (request, isOwner) => html`
<li>
    ${request.user.username}
    ${isOwner ? html`<a @click=${(e) => request.decline} href="javascript:void(0)" class="tm-control action">Remove from team</a>` : ''}
</li>`;

const pendingMemberTemplate = (request) => html`
<li>
    ${request.user.username}
    <a @click=${request.approve} href="javascript:void(0)" class="tm-control action">Approve</a>
    <a @click=${request.decline} href="javascript:void(0)" class="tm-control action">Decline</a>
</li>`;


export async function detailsTeamPage(ctx) {
    // console.log(ctx);
    const teamId = ctx.params.id;

    ctx.renderProp(until(populateTemplate(teamId), loaderTemplate()));

    async function populateTemplate(teamId) {
        const userId = getUserData().id;
        const [team, requests] = await Promise.all([
            getTeamById(teamId),
            getRequestsByTeamId(teamId)
        ]);

        requests.forEach(req => {
            req.approve = (e) => approve(e, req);
            req.decline = (e) => leave(e, req._id);
        });

        const isOwner = userId == team._ownerId;

        //Задаваме ново пропърти - .memberCount

        const members = requests.filter(r => r.status = 'member');
        const pending = requests.filter(r => r.status = 'pending');
        console.log(pending);

        team.memberCount = members.length;


        return teamDetailsTemplate(team, isOwner, createControls, members, pending);

        function createControls() {
            if (userId != null) { //ако имаме user
                const request = requests.find(r => r._ownerId == userId);

                if (isOwner) {//Current user is owner                    
                    return html`<a href=${`/edit/${team._id}`} class="action">Edit team</a>`;
                } else if (request && request.status == "member") {//current user is member
                    return html`<a @click=${e => leave(e, request._id)} href="javascript:void(0)" class="action invert">Leave team</a>`;
                } else if (request && request.status == 'pending') {    //current user has a pending request 
                    return html`Membership pending. <a @click=${e => leave(e, request._id)} href="javascript:void(0)">Cancel request</a>`;
                } else {  //user is not related to the team, but he can join
                    return html`<a @click=${join} href="javascript:void(0)" class="action">Join team</a>`;
                }
            } else {  //ако нямаме user, т.е. Guest visitor
                return '';
            }
        }

        async function join(event) {
            event.target.remove();
            await requestToJoin(teamId);
            ctx.renderProp(await populateTemplate(teamId));
        }

        async function leave(event, requestId) {
            const confirmed = confirm('Are you sure?'); //диалогов прозорец с Да и Не
            if (confirmed) {
                event.target.remove();
                await cancelMembership(requestId);
                ctx.renderProp(await populateTemplate(teamId));
            }
        }

        async function approve(event, request) {
            event.target.remove();
            await approveMembership(request);
            ctx.renderProp(await populateTemplate(teamId));
        }
    }
}


