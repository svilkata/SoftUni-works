function robot() {
    const recipies = {
        apple: { carbohydrate: 1, flavour: 2 },
        lemonade: { carbohydrate: 10, flavour: 20 },
        burger: { carbohydrate: 5, fat: 7, flavour: 3 },
        eggs: { protein: 5, fat: 1, flavour: 1 },
        turkey: { protein: 10, carbohydrate: 10, fat: 10, flavour: 10 },
    };

    const storage = {
        carbohydrate: 0,
        flavour: 0,
        fat: 0,
        protein: 0,
    };

    function restock(element, quantity) {
        storage[element] += Number(quantity);
        return `Success`;
    }

    function prepare(recipe, quantity) {
        const remainingStorage = {};

        for (const el in recipies[recipe]) {
            const remaining = storage[el] - recipies[recipe][el] * quantity;
            if (remaining < 0) {
                return `Error: not enough ${el} in stock`;
            } else {
                remainingStorage[el] = remaining;
            }
        }

        Object.assign(storage, remainingStorage);
        return `Success`;
    }

    function report() {
        return `protein=${storage.protein} carbohydrate=${storage.carbohydrate} fat=${storage.fat} flavour=${storage.flavour}`;
        // protein={qty} carbohydrate={qty} fat={qty} flavour={qty} 
    }

    function control(str) {
        const [command, item, quantity] = str.split(" ");
        switch (command) {
            case 'restock': return restock(item, quantity);
            case 'prepare': return prepare(item, quantity);
            case 'report': return report();
        }
    }

    return control;
}

let manager = robot();
// console.log(manager("restock flavour 50")); // Success 
// console.log(manager("prepare lemonade 4")); // Error: not enough carbohydrate in stock 


console.log(manager("restock flavour 50"));
console.log(manager("prepare lemonade 4"));
console.log(manager("restock carbohydrate 10"));
console.log(manager("restock flavour 10"));
console.log(manager("prepare apple 1"));
console.log(manager("restock fat 10"));
console.log(manager("prepare burger 1"));
console.log(manager("report"));