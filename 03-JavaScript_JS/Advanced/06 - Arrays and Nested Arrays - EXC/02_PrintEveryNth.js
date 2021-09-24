function solve(arr, step) {
    let arrToReturn = [];

    for (let i = 0; i < arr.length; i += step) {
        arrToReturn.push(arr[i]);
    }

    return arrToReturn;
}

