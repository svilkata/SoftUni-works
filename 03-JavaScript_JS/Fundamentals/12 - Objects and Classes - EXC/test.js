let obj = { name: 'Peter', age: '18', grade: '5.50' };
for (let key in obj) {
    console.log(`${key}: ${obj[key]}`); //obj[key] реално е value-то
}
