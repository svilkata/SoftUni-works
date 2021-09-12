function solve(array = []) {
    let resultArray = [];

    for (let i = 0; i < array.length; i++) {
        let currentElement = array[i];
        let rightElementsArray = array.slice(i + 1);
        let isTop = true;

        for (let j = 0; j < rightElementsArray.length; j++) {
            if (currentElement <= rightElementsArray[j]) {
                isTop = false;
                break;
            }
        }

        if (isTop) {
            resultArray.push(currentElement);
        }       

    }

    console.log(resultArray.join(' '));

}

solve([14, 24, 3, 19, 15, 17]);
solve([1, 4, 3, 2]);
