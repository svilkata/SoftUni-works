function magical(matrix = []) {

    function rowsEqual(matrix) {
        let sumEachRow = 0;
        let isSameSumEachRow = true;

        for (let row = 0; row < matrix.length; row++) {
            let currRowSum = 0;
            for (let col = 0; col < matrix[row].length; col++) {
                currRowSum += matrix[row][col];
            }

            if (sumEachRow === 0) {
                sumEachRow = currRowSum;
            } else {
                if (sumEachRow !== currRowSum) {
                    isSameSumEachRow = false;
                    break;
                }
            }
        }

        if (!isSameSumEachRow) {
            return -1;
        } else {
            return sumEachRow;
        }
    }

    function colsEqual(matrix) {
        let sumEachCol = 0;
        let isSameSumEachCol = true;

        for (let col = 0; col < matrix[0].length; col++) {
            let currColSum = 0;
            for (let row = 0; row < matrix.length; row++) {
                currColSum += matrix[row][col];
            }

            if (sumEachCol === 0) {
                sumEachCol = currColSum;
            } else {
                if (sumEachCol !== currColSum) {
                    isSameSumEachCol = false;
                    break;
                }
            }
        }

        if (!isSameSumEachCol) {
            return -1;
        } else {
            return sumEachCol;
        }
    }

    if (rowsEqual(matrix) === -1 || colsEqual(matrix) === -1 ||
        rowsEqual(matrix) !== colsEqual(matrix)) {
        return false;
    } else {
        return true;
    }

}

console.log(
    magical([[4, 5, 6],
    [6, 5, 4],
    [5, 5, 5]]
    ));

console.log(
    magical([[11, 32, 45],
    [21, 0, 1],
    [21, 1, 1]]
    ));

console.log(
    magical([[0, 0, 0],
    [0, 0, 0],
    [0, 0, 0]]
    ));