function solve(arr) {
    arr.sort((a, b) => a - b);
    const middle = Math.floor(arr.length / 2);
    const result = arr.slice(middle);
    return result;
}

// console.log(solve([4, 7, 2, 5]));
console.log(solve([3, 19, 14, 7, 2, 19, 6]));
