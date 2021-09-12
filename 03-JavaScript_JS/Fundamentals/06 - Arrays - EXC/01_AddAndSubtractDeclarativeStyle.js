function solve(data = []) {
    let resultArr = [];
    let originalSum = 0;
    let resultSum = 0;

    data.map((number, index) => {
        number % 2 === 0 ? number += index : number -= index;
        resultArr.push(number);
    });



    console.log(resultArr);
    console.log(originalSum = data.reduce((a, b) => a + b, 0));

    console.log(originalSum = data.reduce((sum, b) => {
        sum += b;
        return sum;
    }, 0));

    iter


    console.log(resultSum = resultArr.reduce((a, b) => a + b, 0));
}

solve([5, 15, 23, 56, 35]);