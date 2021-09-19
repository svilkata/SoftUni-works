function calcFruites(fruit, weightGrams, moneyPerKg) {
    console.log(
        `I need $${(weightGrams / 1000 * moneyPerKg).toFixed(2)} to buy ${(weightGrams / 1000).toFixed(2)} kilograms ${fruit}.`);
}

calcFruites('orange', 2500, 1.80);