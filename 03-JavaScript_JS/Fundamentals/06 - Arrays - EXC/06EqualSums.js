function solve(array = []) {
    let resultArray = [];
    let areEqual = false;

    for (let i = 0; i < array.length; i++) {
        let number = array[i];
        let rightSum = 0;
        let leftSum = 0;

        //right Sum
        for (let j = array.length - 1; j > i; j--) {
            let current = array[j];
            rightSum += current;
        }

        //left Sum
        for (let j = 0; j < i; j++) {
            let current = array[j];
            leftSum += current;
        }

        if (leftSum === rightSum) {
            console.log(i);
            areEqual = true;
            break;
        }
    }

   if (areEqual === false) {
        console.log('no');
    }

}

// solve([1, 2, 3, 3]);
// solve([1, 2, 3]);
// solve([10, 5, 5, 99, 3, 4, 2, 5, 1, 1, 4])
solve([1]);