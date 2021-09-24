function solve(matrix) {
    let sumMainDiagonal = 0;
    let sumSecondDiagonal = 0;

    for (let row = 0; row < matrix.length; row++) {
        sumMainDiagonal += matrix[row][row];
    }

    for (let row = matrix.length - 1, col = 0; row >= 0; row--, col++) {
        sumSecondDiagonal += matrix[row][col];
    }

    console.log(sumMainDiagonal + " " + sumSecondDiagonal);
}

solve([[3, 5, 17],
[-1, 7, 14],
[1, -8, 89]]
);