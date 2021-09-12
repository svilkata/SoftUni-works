function solve(input) {
    let numbers = input;
    let condensed = [];

    while (numbers.length > 1) {
        for (let i = 0; i <= numbers.length-2; i++) {
            condensed.push(numbers[i] + numbers[i+1]);            
        }
        numbers = condensed;
        condensed = [];
    }    

    console.log(numbers[0]);
}

solve([2,10,3]);
solve([5,0,4,1,2]);
solve([1]);