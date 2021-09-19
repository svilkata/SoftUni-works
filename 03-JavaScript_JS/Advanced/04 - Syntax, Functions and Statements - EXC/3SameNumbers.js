function solve(num) {
    let str = num.toString();
    let ch = str[0];
    let sumAllDigits = 0;
    let bll = true;
    for (let i = 0; i < str.length; i++) {

        if (ch !== str[i] && bll === true) {
            bll = false;
        }

        sumAllDigits+= Number(str[i]);        
    }

    console.log(bll);
    console.log(sumAllDigits);

}

solve(2222222);
solve(1234);