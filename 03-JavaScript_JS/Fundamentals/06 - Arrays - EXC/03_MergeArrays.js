function solve(arrayOne = [], arrayTwo = []) {
    let resultArray = [];

    // for (let index in arrayOne) {
    //     if (index % 2 === 0) {
    //         resultArray.push(Number(arrayOne[index]) + Number(arrayTwo[index]));
    //     } else {
    //         resultArray.push(arrayOne[index] + '' +  arrayTwo[index]);
    //     }
    // }

    arrayOne.map((element, index) => {
        index % 2 === 0 ? resultArray.push(Number(element) + Number(arrayTwo[index])) :
        resultArray.push(element + '' +  arrayTwo[index]);
    });

    console.log(resultArray.join(' - '));

}

solve(
    ['5', '15', '23', '56', '35'],
    ['17', '22', '87', '36', '11']
);