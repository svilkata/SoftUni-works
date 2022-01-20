//initialization
// - find relevant section

import { showView } from "./dom.js";

// - detach section from DOM
const section = document.getElementById('edit-movie');
section.remove();

//display logic
export function showEdit() {
    showView(section);
}