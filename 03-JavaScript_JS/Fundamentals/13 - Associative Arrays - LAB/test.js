let things = new Set();
things.add(1);
things.add(2);
things.add(3);
things.add(4);

console.log(things.has(4));

console.log(Array.from(things));
console.log(...things);

