function solve(num) {
    let firstNum = 'a'.charCodeAt(0);
    // let lastNum = firstNum + num;

    for (let i = 0; i < num; i++) {
        for (let j = 0; j < num; j++) {
            for (let k = 0; k < num; k++) {
                let result = String.fromCharCode(firstNum + i, firstNum + j, firstNum + k);

                console.log(result);
            }
        }
    }

}

solve(5);