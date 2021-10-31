function solve(word, startIndex, count) {
    word = word.substr(startIndex, count);

    return word;
}

console.log(solve("ASentance", 1, 8));