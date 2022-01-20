import { getMovieById } from "./api/data.js";

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

//middle-ware = посредник - контекста от едно събитие с next се предава същия контекст и на следващото събитие - идва от page.js
//като модифицираме ctx тук, то наследника по веригата ще го види
export function loadMovie(ctx, next) {
    const moviePromise = getMovieById(ctx.params.id); //не сме го await-нали нарочно
    ctx.moviePromise = moviePromise; //добавяме свойство филм
    next();
}