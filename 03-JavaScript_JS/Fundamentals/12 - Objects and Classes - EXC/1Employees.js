function solve(input = []) {

    for (let i = 0; i < input.length; i++) {
        let currObj = {name: input[i], persNumber : input[i].length,};
        console.log(`Name: ${currObj.name} -- Personal Number: ${currObj.persNumber}`);       
    }
}

solve([
    'Silas Butler',
    'Adnaan Buckley',
    'Juan Peterson',
    'Brendan Villarreal'
    ]
    );