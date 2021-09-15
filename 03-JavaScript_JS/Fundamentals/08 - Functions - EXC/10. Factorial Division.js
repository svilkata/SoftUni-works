function factDivision(numOne, numTwo) {
    function getFactorial(num) {
        let res = 1;

        for (let i = 1; i <= num; i++) {
            res *= i;
        }

        return res;
    }

    let factorialOne = getFactorial(numOne);
    let factorialTwo = getFactorial(numTwo);

    console.log((factorialOne / factorialTwo).toFixed(2));

}

factDivision(2, 3);