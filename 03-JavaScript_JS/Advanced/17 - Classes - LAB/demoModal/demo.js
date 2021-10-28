class Modal {
    constructor(message = "Alert") {
        this.message = message;
        this.element = this._init();
        this.render();
    }

    _init() {
        const overlay = createMyElement('div', { className: 'demo-modal' },
                createMyElement('div', { className: 'modal-dialog' },
                createMyElement('span', {}, this.message),
                button('OK', this.onClose.bind(this)))
        );

        return overlay;
    }

    render() {
        document.body.appendChild(this.element);
    };

    onClose() {
        this.element.remove();
    };

}

function start() {
    new Modal("This is a message");
    // alert(); - няма стилизация alert-а, и браузърът може да блокира ако викаме много alert-и
}

document.querySelector('button').addEventListener('click', start);

function createMyElement(type, props, ...content) {
    const element = document.createElement(type);

    for (let prop in props) {
        element[prop] = props[prop];
    }

    for (let entry of content) {
        if (typeof entry == 'string' || typeof entry == 'number') {
            entry = document.createTextNode(entry);
        }
        element.appendChild(entry);
    }

    return element;
}

function button(label, callback) {
    const element = createMyElement('button', {}, label);
    element.addEventListener('click', callback);
    return element;
}






























