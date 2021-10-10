function solve(...args) {
    for (const arg of args) {
        console.log(typeof arg + ": " + arg);
    }
}

solve('cat',
    42,
    function () {
        console.log('Hello world!');
    });