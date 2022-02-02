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
