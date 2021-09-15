function factDivision(numOne, numTwo) {
    function getFactorial(num) {
        if (num === 1) {
            return 1;
        }
        let res = num * getFactorial(num - 1);
        return res;
    }

    let factorialOne = getFactorial(numOne);
    let factorialTwo = getFactorial(numTwo);

    console.log((factorialOne / factorialTwo).toFixed(2));

}

factDivision(2, 3);