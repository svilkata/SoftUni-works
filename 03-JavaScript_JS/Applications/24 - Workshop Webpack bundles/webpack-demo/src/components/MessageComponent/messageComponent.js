import layoutCss from './MessageComponent.css';

class RootComponent extends HTMLElement {
    constructor() {
        super();

        this.skills = [
            'fast learner',
            'team player',
            'creative person',
            'work under pressure',
            'communicative skills',
            "hello"
        ];
    }


    template(dataSkills) {
        return `
        <div class="message-component-container">
        <h3>List of skills</h3>
        <ul>
            ${dataSkills.map(x => `<skill-card name="${x}"></skill-card>`).join('')}
        </ul>
        </div>`;
    } 

    connectedCallback(){
        this.innerHTML = this.template(this.skills);
    }
}

export default RootComponent;