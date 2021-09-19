/**
 * 
 * @param {string} input1 
 * @param {string} input2 
 * @param {string} input3 
 */

function stringLength(input1, input2, input3) {
    let sum = input1.length + input2.length + input3.length;
    let avg = Math.floor (sum / 3);
    console.log(sum);
    console.log(avg);
    

}

stringLength('chocolate', 'ice cream', 'cake');