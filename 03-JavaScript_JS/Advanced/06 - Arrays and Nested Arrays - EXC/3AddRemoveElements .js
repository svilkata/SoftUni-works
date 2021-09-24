function solve(arr = []) {
    let number = 1;
    let result = [];

    
    arr.forEach(x => {
        if (x === 'add') {
            result.push(number++);
        } else if (x === 'remove') {
            result.pop();
            number++;
        }
    });

    if (result.length === 0) {
        return "Empty";
    } else {
        return result.join("\n");
    }
}

// console.log(solve(['add',
//     'add',
//     'remove',
//     'add',
//     'add']
// ));

console.log(solve(['remove', 
'remove', 
'remove']
));