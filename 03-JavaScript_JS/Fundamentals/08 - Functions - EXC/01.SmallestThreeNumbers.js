function smallestOfThreeNumbers(a, b, c) {
    let min = a;
    if (min > b) {
        min = b;
    }

    if (min > c) {
        min = c;
    }

    console.log(min);
}

smallestOfThreeNumbers(2,
    5,
    3
    );