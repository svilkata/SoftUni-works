function spice(currentYield) {
    let daysCounter = 0;
    let totalSpice = 0;

    while (currentYield >= 100) {
        daysCounter++;
        totalSpice += currentYield;
        totalSpice-= 26;
        currentYield-= 10;
    }
    if (currentYield >= 26 && daysCounter > 0) {
        totalSpice -= 26;
    }
    

    console.log(daysCounter);
    console.log(totalSpice);
}

spice(111);