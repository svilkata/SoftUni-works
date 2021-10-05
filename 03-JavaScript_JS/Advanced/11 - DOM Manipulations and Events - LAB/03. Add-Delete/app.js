function addItem() {
    const input = document.getElementById("newItemText");

    const liElement = document.createElement('li');
    liElement.textContent = input.value;

    //create Delete button
    const button = document.createElement("a");
    button.textContent = '[Delete]';
    button.href = '#';
    button.addEventListener('click', removeElement);

    function removeElement(event) {
        const li = event.target.parentNode;
        console.log(li.remove());
    }

    liElement.appendChild(button);

    document.getElementById("items").appendChild(liElement);

    input.value = "";
}

