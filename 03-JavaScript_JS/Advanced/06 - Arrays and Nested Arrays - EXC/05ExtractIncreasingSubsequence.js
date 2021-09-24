function incrSubs(arr = []) {
    let biggest = Number.MIN_SAFE_INTEGER;
    let result = [];
    // const result = arr.filter(el => {
    //     if (el >= biggest) {
    //         biggest = el;
    //         return true;
    //     }
    //     return false;
    // });

    arr.reduce((accumulated, current) => {
        if (current >= biggest) {
            biggest = current;
            accumulated.push(current);
        }

        return accumulated;
    }, result);

    return result;
}

console.log(
incrSubs([20,
    3,
    2,
    15,
    6,
    1]
));

console.log(
    incrSubs([1, 
        3, 
        8, 
        4, 
        10, 
        12, 
        3, 
        2, 
        24]
        ));