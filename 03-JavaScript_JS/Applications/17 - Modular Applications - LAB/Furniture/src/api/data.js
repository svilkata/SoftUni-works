import * as api from './api.js';

export const login = api.login;
export const register = api.register;
export const logout = api.logout;

const pageSize = 4;

const endpoints = {
    all: '/data/catalog?pageSize=4&offset=',
    count: '/data/catalog?count',  //идва от сървъра
    byId: '/data/catalog/',
    myItems: (userId) => `/data/catalog?where=_ownerId%3D%22${userId}%22`,
    countMyItems: (userId) => `/data/catalog?where=_ownerId%3D%22${userId}%22&count`,
    create: '/data/catalog',
    edit: '/data/catalog/',
    delete: '/data/catalog/'
}

export async function getAll(page, search) {
    let urlGetItems = endpoints.all + (page - 1) * pageSize;
    let urlCountItems = endpoints.count;

    //pagination и search работи - сървъра го прави/връща по правилния начин
    if (search) { //скипва празния стринг
        urlGetItems +='&where=' + encodeURIComponent(`make LIKE "${search}"`); //сървъра в случая търси с &where=attribute LIKE "123"
        urlCountItems += '&where=' + encodeURIComponent(`make LIKE "${search}"`);
    }

    const [data, count] = await Promise.all([
        api.get(urlGetItems),
        api.get(urlCountItems),
    ]);

    return {
        data: data,
        totalPages: Math.ceil(count / pageSize)
    }
}

export async function getMyItems(userId, page, search) {
    let urlGetMyItems = endpoints.all + (page - 1) * pageSize;
    let urlCountMyItems = endpoints.count;

    //pagination и search работи - сървъра го прави/връща по правилния начин
    if (search) { //скипва празния стринг
        urlGetMyItems +='&where=' + encodeURIComponent(`make LIKE "${search}"`); //сървъра в случая търси с &where=attribute LIKE "123"
        urlCountMyItems += '&where=' + encodeURIComponent(`make LIKE "${search}"`);
    }

    const [data, count] = await Promise.all([
        api.get(urlGetMyItems),
        api.get(urlCountMyItems),
    ]);

    return {
        data: data,
        totalPages: Math.ceil(count / pageSize)
    }
}

export async function getById(id) {
    return api.get(endpoints.byId + id);
}

export async function createItem(data) {
    return api.post(endpoints.create, data);
}

export async function editItem(id, data) {
    return api.put(endpoints.edit + id, data);
}

export async function deleteItem(id) {
    return api.del(endpoints.delete + id);
}