function solve(arr1, arr2) {
    let result = '';
    let arrLength = (arr1.length === arr2.length) ? arr1.length
    : arr1.length > arr2.length ? arr1.length : arr2.length;

    let sum = 0;
    for (let i = 0; i < arrLength; i++) {
        if (arr1[i] !== arr2[i]) {
            result += `Arrays are not identical. Found difference at ${i} index`;
            break;
        }
        
        sum+= parseInt(arr1[i]);
    }

    if (result === '') {
        result = `Arrays are identical. Sum: ${sum}`
    }

    console.log(result);
    
}

solve(['1','2','3','4','5'], ['1','2','4','4','5']);
solve(['10','20','30'], ['10','20','30']);