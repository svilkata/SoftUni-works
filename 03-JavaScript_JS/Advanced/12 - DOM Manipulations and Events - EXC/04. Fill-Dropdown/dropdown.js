function addItem() {
    const newText = document.getElementById("newItemText").value;
    const newValue = document.getElementById("newItemValue").value;

    const element = document.createElement("option");
    element.value = newValue;
    element.textContent = newText;
    const menu = document.querySelector("#menu").appendChild(element);

    document.getElementById("newItemText").value = "";
    document.getElementById("newItemValue").value = "";
}