import * as api from './api.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

//urls
const endpoints = {
    allPosts: '/data/posts?sortBy=_createdOn%20desc',
    aPostById: '/data/posts/'
};

export async function getAllPosts() {
    return api.get(endpoints.allPosts);
}

export async function getPostById(id) {
    return api.get(endpoints.aPostById + id);
}
