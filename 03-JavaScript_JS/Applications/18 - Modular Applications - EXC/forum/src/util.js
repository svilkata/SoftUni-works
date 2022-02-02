//вдигаме нивото на абстракция, за да имаме по-лесен достъп до някои от функциите свързани със заявки
export function getUserData() {
    return JSON.parse(sessionStorage.getItem('userData')); 
}

//вдигаме нивото на абстракция, за да имаме по-лесен достъп до някои от функциите свързани със заявки
export function setUserData(data) {
    sessionStorage.setItem('userData', JSON.stringify(data));
}

//вдигаме нивото на абстракция, за да имаме по-лесен достъп до някои от функциите свързани със заявки
export function clearUserData() {
    sessionStorage.removeItem('userData');
}

export function parseQueryString(string) {
    'page=3&search=chair';
    const params = string
        .split('&')
        .map(p => p.split('='))
        .reduce((a, [currKey, currValue]) => Object.assign(a, {[currKey]: currValue}), {});

        return params;
}

export function createSubmitHandler(callback, ...fieldNames) {
    return function (event) {
        event.preventDefault();
        const formData = new FormData(event.target);

        const resultData = {};

        for (let field of fieldNames){
            console.log(field, ': ', formData.get(field));
            resultData[field] = formData.get(field).trim();
        }

        callback(resultData, event); //подаваме и event-а, защото искаме да reset-нем в onSibmit-a на register.js
    };
}