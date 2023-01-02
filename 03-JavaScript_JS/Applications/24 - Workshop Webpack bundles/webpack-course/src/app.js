import MyLibrary from './my-library.js';
import page from './page-2';

import layoutCss from './static/layout.css';
import moadalCss from './static/modal.css';
import siteCss from './static/site.css';
import teamPng from './../../assets/team.png';

const images = {};

function importAll(r) {
    return r.keys().forEach((key) => {
        let imageName = key.substring(key.lastIndexOf('/'));
        images[imageName] = r(key);
    })    
}

importAll(require.context('/assets', false, /\.(png|jpe?g|svg)$/));


MyLibrary();

class Foo {
    constructor(name) {
        this.name = name;
    }
}

page.call();

console.log(new Foo('pesho').name);










