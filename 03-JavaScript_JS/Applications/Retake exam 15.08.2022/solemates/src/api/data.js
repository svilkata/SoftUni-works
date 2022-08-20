import * as api from './api.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

//urls
const endpoints = {
    allShoesPosts: '/data/shoes?sortBy=_createdOn%20desc',
    createItem: '/data/shoes',
    itemDetailsPlus: '/data/shoes/'
};

export async function getAllShoesPosts() {
    return api.get(endpoints.allShoesPosts);
}

export async function addTheNewItem(pairOfShoes) {
    return api.post(endpoints.createItem, pairOfShoes);
}

export async function getAnItemById(itemId) {
    return api.get(endpoints.itemDetailsPlus + itemId);
}

export async function deleteItemById(itemId) {
    return api.del(endpoints.itemDetailsPlus + itemId);
}


export async function editTheNewItemById(itemId, item) {
    return api.put(endpoints.itemDetailsPlus + itemId, item);
}

export async function searchToFindItems(query) {
    return api.get(`/data/shoes?where=brand%20LIKE%20%22${query}%22`);
}

