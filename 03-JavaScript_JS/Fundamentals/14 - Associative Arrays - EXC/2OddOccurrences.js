function solve(input) {
    let arr = input.toLowerCase().split(' ');
    let words = {};
    arr.forEach(w => {
        if (words[w] === undefined) {
            words[w] = 0;
        }
        words[w] += 1;
    });

    let output = [];
    Object.entries(words)
    .forEach(entry => {
        if (entry[1] % 2 !== 0) {
            output.push(entry[0]);
        }
    })

    return output.join(" ");

}

console.log(
solve('Java C# Php PHP Java PhP 3 C# 3 1 5 C#'));