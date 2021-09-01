function sumOddNumbers(number) {
    let sum = 0;
    let counter = 0;
    let currentNumber = 1;

    while (counter < number) {
        if (currentNumber % 2 !== 0) {
            console.log(currentNumber);
            sum += currentNumber;
            counter++;
        }
        currentNumber++;
    }     


    console.log('Sum: ' + sum);
}

sumOddNumbers(5);

