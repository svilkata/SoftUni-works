function solve(word, text) {
    while (text.indexOf(word) >= 0) {
        text = text.replace(word, "");
    }

    return text;
}

console.log(solve("ice", "kicegiciceeb"));