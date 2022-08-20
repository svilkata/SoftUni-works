import * as api from './api.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

//urls
const endpoints = {
    allPosts: '/data/posts?sortBy=_createdOn%20desc',
    createAPost: '/data/posts',
    postDetailsPlus: '/data/posts/'
};

export async function getAllPosts() {
    return api.get(endpoints.allPosts);
}

export async function createAPost(post) {
    return api.post(endpoints.createAPost, post);
}

export async function getAPostById(id) {
    return api.get(endpoints.postDetailsPlus + id);
}

export async function deleteAPostById(id) {
    return api.del(endpoints.postDetailsPlus + id);
}

export async function editAPostById(postId, post) {
    return api.put(endpoints.postDetailsPlus + postId, post);
}

export async function getCurrentlyLoggedInUserPosts(userId) {
    return api.get(`/data/posts?where=_ownerId%3D%22${userId}%22&sortBy=_createdOn%20desc`);
}

