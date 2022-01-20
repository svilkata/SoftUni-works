//initialization
// - find relevant section

import { showView } from "./dom.js";

// - detach section from DOM
const section = document.getElementById('add-movie');
section.remove();

//display logic
export function showCreate() {
    showView(section);
}