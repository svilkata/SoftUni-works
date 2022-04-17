import { clearUserData, getUserData, setUserData } from "../utils.js";

const host = 'http://localhost:3030';

async function request(url, options) {
    try {
        const response = await fetch(host + url, options);

        if (response.ok != true) {
            if (response.status == 403) {
                clearUserData(); //from utils.js
            }

            const error = await response.json();
            throw new Error(error.message);
        }

        if (response.status == 204) {
            return response;
        } else {
            return response.json();
        }

    } catch (err) {
        alert(err.message);
        throw err;
    }
}

function createOptions(method = 'get', data) {
    const options = {
        method,
        headers: {}
    };

    if (data != undefined) {
        options.headers['Content-Type'] = 'application/json';
        options.body = JSON.stringify(data);
    }

    const userData = getUserData(); //from utils.js
    if (userData != null) {
        options.headers['X-Authorization'] = userData.token;
    }

    return options;
}

export async function get(url) {
    return request(url, createOptions());
}

export async function post(url, data) {
    return request(url, createOptions('post', data));
}

export async function put(url, data) {
    return request(url, createOptions('put', data));
}

export async function del(url) {
    return request(url, createOptions('delete'));
}

export async function login(email, password) {
    const result = await post('/users/login', {email, password});
    //Оттук надолу заявката ще се изпълни успешно само ако сме минали през горния ред успешно

    const userData = {
        email: result.email,
        id: result._id,
        token: result.accessToken
    };

    setUserData(userData); //from utils.js
}

export async function register(email, password) {
    const result = await post('/users/register', {email, password});
    //Оттук надолу заявката ще се изпълни успешно само ако сме минали през горния ред успешно

    const userData = {
        email: result.email,
        id: result._id,
        token: result.accessToken
    };

    setUserData(userData); //from utils.js
}

export async function logout(){
    await get('/users/logout');
    clearUserData(); //from utils.js
}

// //Или асинхронно, поред едно след друго се изпълнява – виж app.js
// export async function logout(){
//     get('/users/logout');
//     clearUserData(); //from utils.js
// }
