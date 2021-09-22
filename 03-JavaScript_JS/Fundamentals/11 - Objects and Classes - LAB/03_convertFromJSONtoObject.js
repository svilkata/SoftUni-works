// let personJSON = `{
//     "name": "Ivan",
//     "age": 25,
//     "height": 190
// }`;

// let personObject = JSON.parse(personJSON);

// console.log(personObject.name);

// let personJS = {
//     name : "Stamat",
//     age : 22
// };

// let personJSON = JSON.stringify(personJS);
// console.log(personJSON);

function solve(inputJSON) {
    let info = JSON.parse(inputJSON);

    for (let key in info) {
        console.log(`${key}: ${info[key]}`);
    }
}

solve('{"name": "George", "age": 40, "town": "Sofia"}');