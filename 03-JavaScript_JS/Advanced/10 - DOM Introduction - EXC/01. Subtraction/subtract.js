function subtract() {
    const result = document.getElementById("result");
    const first = Number(document.getElementById("firstNumber").value);
    const second = Number(document.getElementById("secondNumber").value);

    result.innerHTML = (first - second).toString();    
}