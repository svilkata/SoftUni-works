import * as api from './api.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

//Implement application-specific request

//Team collection
export async function getTeams() {
    const teams = await api.get('/data/teams');
    const members = await getMembers(teams.map(t => t._id));
    teams.forEach(t => t.memberCount = members.filter(m => m.teamId == t._id).length);

    console.log(teams);
    //TODO attach members to each team
    return teams;
}

export async function getTeamById(id) {
    return await api.get('/data/teams/' + id);
}

export async function createTeam(team) {
    return await api.post('/data/teams', team);
}

export async function editTeam(id, team) {
    return await api.put('/data/teams/' + id, team);
}

export async function deleteTeam(id) {
    return await api.del('/data/teams/' + id);
}

export async function requestToJoin(teamId) {
    const body = {teamId};
    return await api.post('/data/members', body);
}

//Members collection
export async function getRequestsByTeamId(teamId) {
    return await api.get(`/data/members?where=teamId%3D%22${teamId}%22&load=user%3D_ownerId%3Ausers`);
}

export async function getMembers(teamIds) {
    const query = encodeURIComponent(`teamId IN ("${teamIds.join('", "')}") AND status="member"`);
    return await api.get(`/data/members?where=${query}&count`);
}

export async function cancelMembership(requestId) {
    return await api.del('/data/members/' + requestId);
}

export async function approveMembership(request) {
    //Копие на request-a

    const body = {
        teamId : request.teamId,
        status: 'member'
    };

    return await api.put('/data/members/' + request._id, body);
}

