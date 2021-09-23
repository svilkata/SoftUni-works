function solve(input = []) {
    let targetWords = input.shift().split(" ");
    let words = {};

    targetWords.forEach(word => words[word] = 0);
    let a = 5;

    input.forEach(w => {
        if (targetWords.includes(w)) {
            words[w] += 1;
        }
    });

    let b = 10;

    Object.entries(words)
    .sort((a, b) => b[1] - a[1])
    .forEach(x => console.log(`${x[0]} - ${x[1]}`));

}

solve([
    'this sentence', 'In', 'this', 'sentence', 'you', 'have', 'to', 'count', 'the', 'occurances', 'of', 'the'
    , 'words', 'this', 'and', 'sentence', 'because', 'this', 'is', 'your', 'task'
]
);