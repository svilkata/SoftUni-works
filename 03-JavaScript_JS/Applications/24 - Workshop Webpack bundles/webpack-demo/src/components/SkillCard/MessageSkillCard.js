import './SkillCard.css';

class SkillCard extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
        <li>${this.getAttribute('name')}</li>
        `;
    }
}

export default SkillCard;