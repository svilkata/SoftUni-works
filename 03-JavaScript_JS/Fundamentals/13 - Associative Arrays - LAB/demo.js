let assocArr = {};
assocArr['one'] = 1;
assocArr['two'] = 2;
assocArr['three'] = 3;

Object.keys(assocArr)
.forEach((i) => { 
    console.log(`${i} = ${assocArr[i]}`)
});

Object.entries(assocArr)
.forEach((kvp) => { 
    console.log(`${kvp[0]} = ${kvp[1]}`)
});

let phonebookMapToObject = Object.fromEntries(phonebook.entries());
let phonebookMap = new Map(Object.entries(phonebookObject));















