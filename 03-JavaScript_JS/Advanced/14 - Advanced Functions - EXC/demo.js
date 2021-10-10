const obj = {
    id: 10,
    author: "Ivan",
    content: "AlaBalaNitsa",
    upvotes: 5,
    downvotes: 10,
}

function test() {
    this.upvotes += 8;
}

console.log(obj);
test.call(obj);
console.log(obj);
