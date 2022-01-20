const host = 'http://localhost:3030';

//функция на ниско ниво, извършва проверките
async function request(url, options) {
    try {
        const response = await fetch(host + url, options);

        if (response.ok != true) { //има network грешка
            if (response.statu == 403) {
                sessionStorage.removeItem('userData');
            }

            const error = await response.json(); //network-а връща грешка, която стои в body-то
            throw new Error(error.message);
        }

        // try {
        //     const data = await response.json();
        //     return data;
        // } catch (er) {
        //     return response;
        // }

        if (response.status == 204) {
            return response;
        } else {
            return response.json(); //няма нужда от await в случая
        }

    } catch (err) {
        alert(err.message)
        throw err;
    }
}

//тази функция също е на ниско ниво
function createOptions(method = 'get', data) {
    const options = {
        method,
        headers: {}
    }

    if (data != undefined) {
        options.headers['Content-Type'] = 'application/json';
        options.body = JSON.stringify(data);
    }

    const userData = JSON.parse(sessionStorage.getItem('userData'));
    if (userData != null) {
        options.headers['X-Authorization'] = userData.token;
    }

    return options;
}

//функции на високо ниво
async function get(url) {
    return request(url, createOptions());
}

//функции на високо ниво
async function post(url, data) {
    return request(url, createOptions('post', data));
}

//функции на високо ниво
async function put(url, data) {
    return request(url, createOptions('put', data));
}

//функции на високо ниво
async function del(url) {
    return request(url, createOptions('delete'));
}

async function login(email, password) {
    const response = await request('/users/login',createOptions('post', {email, password}));
    const userData = {
        email: response.email,
        id: response._id,
        token: response.accessToken
    };
    sessionStorage.setItem('userData', JSON.stringify(userData));
}

async function register(email, password) {
    const response = await request('/users/register',createOptions('post', {email, password}));
    const userData = {
        email: response.email,
        id: response._id,
        token: response.accessToken
    };
    sessionStorage.setItem('userData', JSON.stringify(userData));
}

async function logout() {
    //резулатата не ни интересува, но трябва да го изчакаме
    await request('/users/logout', createOptions());

    sessionStorage.removeItem('userData');
}

export {
    get,
    post,
    put,
    del,
    login,
    register,
    logout
}