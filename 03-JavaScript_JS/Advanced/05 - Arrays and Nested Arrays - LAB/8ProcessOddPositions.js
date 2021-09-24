function solve(numbers = []) {
    const oddNums = numbers
        .filter((value, index) => index % 2 == 1)
        .map(x => x * 2)
        .reverse();

    console.log(oddNums.join(" "));
}

solve([10, 15, 20, 25]);