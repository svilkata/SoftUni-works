function gladiatorExpenses(lostFights, helmetPrice, swordPrice, shieldPrice, armorPrice) {
    let brokenHelmets = 0;
    let brokenSwords = 0;
    let brokenShields = 0;
    let brokenArmors = 0;

    for (let i = 1; i <= lostFights; i++) {
        if (i % 12 === 0) {
            brokenArmors++;
            brokenShields++;
            brokenSwords++;
            brokenHelmets++;  
        } else if (i % 6 === 0) {
            brokenShields++;
            brokenSwords++;
            brokenHelmets++;  
        } else if (i % 3 === 0) {
            brokenSwords++;
        } else if (i % 2 === 0) {
            brokenHelmets++;
        }
    }

    let totalExpenses = brokenHelmets * helmetPrice + brokenSwords * swordPrice + brokenShields * shieldPrice
    + brokenArmors * armorPrice;
    console.log(`Gladiator expenses: ${totalExpenses.toFixed(2)} aureus`);
    

}

gladiatorExpenses(7,
    2,
    3,
    4,
    5
);