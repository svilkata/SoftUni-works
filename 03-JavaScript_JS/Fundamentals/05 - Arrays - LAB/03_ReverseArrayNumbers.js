function solve(num, numbers) {
    let arr = [];
    for (let i = 0; i < num; i++) {
        arr[i] = numbers[i];        
    }


    let result = '';
    for (let i = arr.length-1; i >= 0; i--) {
        result+= arr[i] + ' ';        
    }

    console.log(result);
}

solve(3, [10, 20, 30, 40, 50]);