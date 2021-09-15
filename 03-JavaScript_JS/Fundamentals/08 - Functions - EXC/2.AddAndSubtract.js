function addAndSubtract(a, b, c) {
    function sum(numOne, numTwo) {
        return numOne + numTwo;
    }

    function subtract(num1, num2) {
        return num1 - num2;
    }

    let sumResult = sum(a, b);
    let finalResult = subtract(sumResult, c);
    console.log(finalResult);    
}

addAndSubtract(1,
    17,
    30
    );