function amazeNumbers(num) {
    let currentNumber = num;
    let sum = 0;

    while (currentNumber > 0) {
        let digit = currentNumber % 10;
        sum += digit;
        currentNumber = Math.trunc(currentNumber / 10);
    }

    if (sum.toString().includes(9)) {
        console.log(`${num} Amazing? True`);
    } else {
        console.log(`${num} Amazing? False`);
    }
}

amazeNumbers(1233);

