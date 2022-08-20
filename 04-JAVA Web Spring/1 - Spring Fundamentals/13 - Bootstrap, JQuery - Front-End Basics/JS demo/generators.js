//безкраен цикъл
function* getIdGenerator() {
    let lastId = 0;
    while (true) {
        lastId++;
        yield lastId;  //като return работи, но изчакай и за следващо извикване
    }
}

const idGenerator1 = getIdGenerator();
const idGenerator2 = getIdGenerator();

console.log(idGenerator1.next());
console.log(idGenerator1.next());
console.log(idGenerator1.next());
console.log(idGenerator2.next());
console.log(idGenerator1.next());

const idGenerator = getIdGenerator();

for (let id of idGenerator) {
    console.log(id);
    if (id > 10) {
        break;
    }
}


