function sortNums(arr = []) {
    arr.sort((a, b) => a - b);
    let resultArr = [];

    while (arr.length > 0) {
        if (arr.length !== 0) {
            resultArr.push(arr.shift());
        }

        if (arr.length !== 0) {
            resultArr.push(arr.pop());
        }
    }

    return resultArr;
}

console.log(
    sortNums([1, 65, 3, 52, 48, 63, 31, -3, 18]));