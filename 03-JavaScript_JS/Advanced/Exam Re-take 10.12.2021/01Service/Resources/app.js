window.addEventListener('load', solve);

function solve() {
    const enterData = document.querySelector('#right form');
    const buttonSendForm = enterData.querySelector('button');
    buttonSendForm.addEventListener('click', repairFormSend);

    const receivedOrders = document.querySelector('#wrapper #received-orders');
    const completedOrders = document.querySelector('#wrapper #completed-orders');

    const btnClear = completedOrders.querySelector('.clear-btn');
    btnClear.addEventListener('click', removeAllCompletedOrders);

    function repairFormSend(ev) {
        ev.preventDefault();
        const typeProduct = enterData.querySelector('#type-product');
        const description = enterData.querySelector('#description');
        const clientName = enterData.querySelector('#client-name');
        const clientPhone = enterData.querySelector('#client-phone');

        if (description.value != "" && clientName.value != "" && clientPhone.value != "") {
            const el = document.createElement("div");
            el.className = "container";

            const h2 = document.createElement("h2");
            h2.textContent = `Product type for repair: ${typeProduct.value}`;
            el.appendChild(h2);

            const h3 = document.createElement("h3");
            h3.textContent = `Client information: ${clientName.value}, ${clientPhone.value}`;
            el.appendChild(h3);

            const h4 = document.createElement("h4");
            h4.textContent = `Description of the problem: ${description.value}`;
            el.appendChild(h4);

            const btnStart = document.createElement("button");
            btnStart.className = "start-btn";
            btnStart.textContent = "Start repair";
            el.appendChild(btnStart);
            btnStart.addEventListener('click', startReparing);

            const btnFinish = document.createElement("button");
            btnFinish.className = "finish-btn";
            btnFinish.textContent = "Finish repair";
            btnFinish.disabled = true;
            el.appendChild(btnFinish);

            typeProduct.value = "Computer";
            description.value = "";
            clientName.value = "";
            clientPhone.value = "";

            receivedOrders.appendChild(el);
        }
    }

    function startReparing(ev) {
        // ev.preventDefault();

        const btnStart = ev.currentTarget;
        btnStart.disabled = true;

        const btnFinish = ev.currentTarget.parentNode.querySelector('.finish-btn');
        btnFinish.disabled = false;
        btnFinish.addEventListener('click', finishReparing);
    }

    function finishReparing(ev) {
        // ev.preventDefault();

        const infoToMove = ev.currentTarget.parentNode;

        const typeProduct = infoToMove.querySelector('div h2');
        const namePhone = infoToMove.querySelector('div h3');
        const description = infoToMove.querySelector('div h4');

        infoToMove.remove();

        const el = document.createElement("div");
        el.className = "container";

        el.appendChild(typeProduct);
        el.appendChild(namePhone);
        el.appendChild(description);
        completedOrders.appendChild(el);
    }

    function removeAllCompletedOrders(ev) {
        //         ev.currentTarget.parentNode.innerHTML = 
        // `<section id="completed-orders">
        // <h2>Completed orders:</h2>
        // <img src="./style/img/completed-order.png">
        // <button class="clear-btn">Clear</button>
        // </section>`;

        const el = ev.currentTarget.parentNode;
        const elementsToRemove = el.querySelectorAll('.container');
        elementsToRemove.forEach(clasContainerElement => clasContainerElement.parentNode.removeChild(clasContainerElement));
    }
}