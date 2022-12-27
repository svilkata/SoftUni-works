import { logout} from "./api/data.js";
import { page, render } from "./lib.js";
import { getUserData } from "./util.js";
import { catalogPage } from "./views/catalog.js";
import { createPage } from "./views/create.js";
import { detailsPage } from "./views/details.js";
import { editPage } from "./views/edit.js";
import { loginPage } from "./views/login.js";
import { registerPage } from "./views/register.js";


const root = document.querySelector('div.container');
document.getElementById('logoutBtn').addEventListener('click', onLogout);

page(decorateContext); //преди всички останали, глобален middle-ware
page('/', catalogPage);
page('/details/:id', detailsPage);
page('/create', createPage);
page('/edit/:id', editPage);
page('/login', loginPage);
page('/register', registerPage);
page('/my-furniture', catalogPage);

updateUserNav();
page.start();

function decorateContext(ctx, next) {
    ctx.renderProp = (content) =>  render(content, root);
    ctx.updateUserNav = updateUserNav;
    next();
}


function updateUserNav() {
    const userData = getUserData();
    if (userData) {
        document.getElementById('user').style.display = 'inline-block';
        document.getElementById('guest').style.display = 'none';
    } else {
        document.getElementById('user').style.display = 'none';
        document.getElementById('guest').style.display = 'inline-block';
    }
}



//няма нужда тук да викаме preventDefault на event-a, защото в html-a имаме
//<a id="logoutBtn" href="javascript:void(0)">Logout</a>
async function onLogout() {
    await logout();
    updateUserNav();// в съшия scope
    page.redirect('/');
}
