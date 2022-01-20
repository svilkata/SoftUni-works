import { showSection } from "./dom.js";

const homeSection = document.getElementById('homeSection');
homeSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването

const aboutSection = document.getElementById('aboutSection');
aboutSection.remove(); //откачаме от DOM дървото - по-добър вариант от скриването

export function showHomePage() {
    showSection(homeSection);
}

export function showAboutPage() {
    showSection(aboutSection);
}