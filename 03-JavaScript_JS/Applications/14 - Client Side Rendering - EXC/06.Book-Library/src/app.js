import { showCatalog } from './catalog.js';
import { showCreate } from './create.js';
import { showUpdate } from './update.js';
import { render } from './utility.js';

//main module:
// init other modules with dependencies
// - rendering
// - communication between modules
const root = document.body; //тук го използваме без querySelector

const ctx = {
    updateRender
};

updateRender();

//пререднира/изпълянва и трите визуализации на body html структурата 
function updateRender() {
    render([
        showCatalog(ctx),
        showCreate(ctx),
        showUpdate(ctx)],
        root);
}








