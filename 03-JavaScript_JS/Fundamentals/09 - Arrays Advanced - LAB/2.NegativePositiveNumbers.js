function solve(input) {
    let newArr = [];

    for (const number of input) {
        if (number >= 0) {
            newArr.push(number);
        } else {
            newArr.unshift(number);
        }
    }

    for (const element of newArr) {
        console.log(element);
    }
    
}

solve([7, -2, 8, 9]);
solve([3, -2, 0, -1]);