function add(num) {
    let sum = num;

    function addInternal(num2) {
        sum += num2;
        return addInternal;
    }

    addInternal.toString = () => {
        return sum;
    }

    return addInternal;
}

// console.log(add(1).toString());
console.log(add(1)(6)(-3).toString());