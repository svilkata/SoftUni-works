function rotate(arr = [], rotations) {
    for (let i = 1; i <= rotations % arr.length; i++) {
        arr.unshift(arr.pop());        
    }

    return arr.join(" ");
}

console.log(
    rotate(['1',
        '2',
        '3',
        '4'],
        2
    ));