function gameFactory() {
    let { wizard, bugStats, fireballStats } = state;

    let startScreen = document.querySelector('.start-screen');
    let playScreen = document.querySelector('.play-screen');
    let scoreScreen = document.querySelector('.score-screen');
    let wizardElement = createWizard(wizard.x, wizard.y);

    playScreen.appendChild(wizardElement);

    let factory = {
        startScreen,
        playScreen,
        scoreScreen,
        wizardElement,
        createBug: () => {
            let bugElement = document.createElement('div');
        
            bugElement.classList.add('bug');
            bugElement.style.width = bugStats.width + 'px';
            bugElement.style.height = bugStats.height + 'px';

            bugElement.style.left = playScreen.offsetWidth - bugStats.width + 'px';
            bugElement.style.top = (playScreen.offsetHeight - bugStats.height) * Math.random() + 'px';

            playScreen.appendChild(bugElement);
        },
        createFireball: () => {
            let fireballElement = document.createElement('div');

            fireballElement.classList.add('fireball');
            fireballElement.style.width = fireballStats.width + 'px';
            fireballElement.style.height = fireballStats.height + 'px';

            fireballElement.style.left = wizard.x + 'px';
            fireballElement.style.top = wizard.y + 'px';

            playScreen.appendChild(fireballElement);
        }
    };

    return factory;
}

function createWizard(posX, posY) {
    let wizardElement = document.createElement('div');
    wizardElement.classList.add('wizard');
    wizardElement.style.top = posY + 'px';
    wizardElement.style.left = posX + 'px';

    return wizardElement;
}
