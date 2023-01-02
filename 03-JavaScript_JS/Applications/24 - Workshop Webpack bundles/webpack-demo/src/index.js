import RootComponent from "./components/MessageComponent/messageComponent.js";
import SkillCard from "./components/SkillCard/MessageSkillCard.js";

customElements.define('message-component', RootComponent);
customElements.define('skill-card', SkillCard);

const rootElement = document.getElementById('root');

rootElement.innerHTML = '<message-component></message-component>';

console.log('js initialized!');