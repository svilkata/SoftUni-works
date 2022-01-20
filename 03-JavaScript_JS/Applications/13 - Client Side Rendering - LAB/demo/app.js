import { renderTemplate } from "./engine.js";

async function start() {
    const data = await (await fetch('./data.json')).json();//това го правихме на 2 реда, сега го правим на един ред
    const templateAsString = await (await fetch('./article.html')).text(); //HTML не е json

    const main = document.querySelector('main');

    // const result = renderTemplate(template, data[0]);

    const template = renderTemplate(templateAsString);
    main.innerHTML = data.map(template).join("");
}

start();