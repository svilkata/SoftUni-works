function solve(integers = [], commands = []) {
    let currCommand = commands.shift();

    function add(arr = [], tokens) {
        arr.splice(tokens[0], 0, parseInt(tokens[1]));
        return arr;
    }

    function addMany(arr = [], tokens) {
        let index = tokens.shift();
        for (let value of tokens) {
            arr.splice(index++, 0, value)
        }

        return arr;
    }

    function remove(arr = [], tokens) {
        let index = parseInt(tokens[0]);
        arr.splice(index, 1);

        return arr;
    }

    function shift(arr, tokens) {
        let pos = parseInt(tokens[0]);
        while (pos > 0) {
            let el = arr.shift();
            arr.push(el);
            pos--;
        }

        return arr;
    }

    function sumPairs(arr = []) {
        let output = [];
        let a = arr.shift() || 0;
        let b = arr.shift() || 0;

        while (arr.length > 0) {
            output.push(a + b);
        }

        return output;
    }

    while (currCommand !== 'print') {
        let tokens = currCommand.split(" ");
        switch (tokens.shift()) {
            case 'add':
                integers = add(integers, tokens);
                break;
            case 'addMany':
                integers = addMany(integers, tokens);
                break;
            case 'contains':
                console.log(integers.indexOf(Number(tokens[0])));
                break;
            case 'remove':
                integers = remove(integers, tokens);
                break;
            case 'shift':
                integers = shift(integers, tokens);
                break;
            case 'sumPairs':
                integers = sumPairs(integers);
                break;
        }

        currCommand = commands.shift();
    }

    return `[ ${integers.join(", ")} ]`
}

console.log(solve(
    [1, 2, 4, 5, 6, 7],
    ['add 1 8', 'contains 1', 'contains 3', 'print']
));

// console.log(solve([1, 2, 3, 4, 5],
//     ['addMany 5 9 8 7 6 5', 'contains 15', 'remove 3', 'shift 1', 'print']
// ));