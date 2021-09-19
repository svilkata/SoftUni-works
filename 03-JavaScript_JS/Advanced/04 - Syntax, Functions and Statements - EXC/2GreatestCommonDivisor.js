/**
 * 
 * @param {Number} a 
 * @param {Number} b 
 */
function gcd(a, b) {
    while (b !== 0) {
        const temp = b;
        b = a % b;
        a = temp;
    }

    return a;
}

console.log(gcd(5, 15));