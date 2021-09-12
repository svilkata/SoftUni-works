function solve(arrayOfNums = []) {
    let output = arrayOfNums
    .map(Number)
    .map(x => x+ 2)
    .join(' ');
    
    console.log(output, typeof output, '3d argument');
}

solve(['1', '2', '3', '4']);