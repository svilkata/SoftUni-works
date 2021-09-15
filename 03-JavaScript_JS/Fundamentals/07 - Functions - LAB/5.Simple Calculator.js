function solve(firstOperand, secondOperand, operation) {
    const multiply = (a, b) => a * b;
    const divide = (a, b) => a / b;
    const add = (a, b) => a + b;
    const subtract = (a, b) => a - b;

    switch (operation) {
        case 'multiply': console.log(multiply(firstOperand, secondOperand)); break;
        case 'divide': console.log(divide(firstOperand, secondOperand)); break;
        case 'add': console.log(add(firstOperand, secondOperand)); break;
        case 'subtract': console.log(subtract(firstOperand, secondOperand)); break;
    }

}

solve(5, 4, 'multiply');