function solve(input = []) {
    let phonebook = {};

    for (let i = 0; i < input.length; i++) {
        let entry = input[i].split(" ");
        let name = entry[0];
        let number = entry[1];

        phonebook[name] = number;        
    }

    for (const name in phonebook) {
        console.log(`${name} -> ${phonebook[name]}`);
    }
}

solve(['Tim 0834212554',
    'Peter 0877547887',
    'Bill 0896543112',
    'Tim 0876566344']
);