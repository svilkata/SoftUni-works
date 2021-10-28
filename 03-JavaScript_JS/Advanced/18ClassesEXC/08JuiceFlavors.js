function solve(input) {
    let map = new Map();
    let bottlesMap = new Map();

    input.forEach(el => {
        let [fruit, mls] = el.split(" => ");
        mls = Number(mls);
        if (!map.has(fruit)) {
            map.set(fruit, mls);
        } else {
            let currMls = map.get(fruit);
            map.set(fruit, currMls + mls);
        }

        if (map.get(fruit) >= 1000) {
            let countBottlesOfThatFruit = parseInt(map.get(fruit) / 1000.0);
            mls = mls % 1000;
            map.set(fruit, mls);
            if (!bottlesMap.has(fruit)) {
                bottlesMap.set(fruit, 0);
            }

            let currBottlesInBottlesMapOfThatFruit = bottlesMap.get(fruit);
            bottlesMap.set(fruit, currBottlesInBottlesMapOfThatFruit + countBottlesOfThatFruit);
        }
    });

    for (const [key, value] of bottlesMap.entries()) {
        console.log(key + " => " + value);
    }
}


/*solve(['Orange => 2000',
    'Peach => 1432',
    'Banana => 450',
    'Peach => 600',
    'Strawberry => 549']
);*/

solve(['Kiwi => 234',
'Pear => 2345',
'Watermelon => 3456',
'Kiwi => 4567',
'Pear => 5678',
'Watermelon => 6789']
);