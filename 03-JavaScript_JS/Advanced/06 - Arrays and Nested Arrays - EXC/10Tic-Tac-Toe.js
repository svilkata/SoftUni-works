function solve(arr = []) {
    const playField = [
        [false, false, false],
        [false, false, false],
        [false, false, false]
    ];

    let gameWon = false;

    function hasWin(field, coordinates) {
        const x = coordinates[0];
        const y = coordinates[1];

        if (field[0][y] === field[1][y] && field[0][y] === field[2][y] && field[x][y] !== false) {
            return true;
        } else if (field[x][0] === field[x][1] && field[x][0] === field[x][2] && field[x][y] !== false) {
            return true;
        } else if ((field[0][0] === field[1][1] && field[0][0] === field[2][2] && field[1][1] !== false) ||
        (field[2][0] === field[1][1] && field[2][0] === field[0][2] && field[2][0] !== false)) {
            return true;
        } else {
            return false;
        }
    }

    function printResult(field) {
        field.forEach(element => {
            console.log(element.join("\t"));
        });
    }

    let currPlayerSign = "X";
    let inputMax9 = Math.min(9, arr.length);

    for (let i = 0; i < inputMax9; i++) {
        const tokens = arr[i].split(" ");
        const currPoint = [parseInt(tokens[0]), parseInt(tokens[1])];
   
        if (playField[currPoint[0]][currPoint[1]] !== false) {
            console.log("This place is already taken. Please choose another!");
        } else {
            playField[currPoint[0]][currPoint[1]] = currPlayerSign;
            if (hasWin(playField, currPoint)) {
                gameWon = true;
                console.log(`Player ${currPlayerSign} wins!`);
                printResult(playField);
                break;
            }
            currPlayerSign = currPlayerSign == 'X' ? 'O' : 'X';
        }
    }

    if (!gameWon) {
        console.log('The game ended! Nobody wins :(');
        printResult(playField);
    }

}

/*solve(["0 1",
    "0 0",
    "0 2",
    "2 0",
    "1 0",
    "1 1",
    "1 2",
    "2 2",
    "2 1",
    "0 0"]
);*/

/*solve(["0 0",
"0 0",
"1 1",
"0 1",
"1 2",
"0 2",
"2 2",
"1 2",
"2 2",
"2 1"]
);*/

solve(["0 1",
"0 0",
"0 2",
"2 0",
"1 0",
"1 2",
"1 1",
"2 1",
"2 2",
"0 0"]
);