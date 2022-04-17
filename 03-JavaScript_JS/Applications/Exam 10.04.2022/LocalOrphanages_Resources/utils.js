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
