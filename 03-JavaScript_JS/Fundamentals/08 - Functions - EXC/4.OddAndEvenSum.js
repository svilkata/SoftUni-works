function oddAndEvenSum(input) {
    let sumOdd = 0;
    let sumEven = 0;

    while (input / 10 > 0) {
        let currentDigit = input % 10;
        if (currentDigit % 2 === 0 ) {
            sumEven+= currentDigit;
        } else {
            sumOdd+= currentDigit;
        }

        input = Math.floor(input / 10);
    }

    for (let i = 0; i < input.length; i++) {
        
        
    }

    console.log(`Odd sum = ${sumOdd}, Even sum = ${sumEven}`);
}

oddAndEvenSum(1000435);
oddAndEvenSum(3495892137259234);