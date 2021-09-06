function mining(data) {
    let goldValue = 67.51;
    let bitcoinValue = 11949.16;

    let bitcoinCounter = 0;
    let firstDay;
    let totalMoney = 0;

    for (let i = 0; i < data.length; i++) {
        let goldMined = data[i];

        if ((i+1) % 3 === 0) {
            goldMined *= 0.7;
        }
        totalMoney += goldMined * goldValue;

        if (totalMoney >= bitcoinValue) {
            if (firstDay === undefined) {
                firstDay = i+1;
            }
            bitcoinCounter += parseInt(totalMoney / bitcoinValue);
            // totalMoney -= bitcoinValue;
            totalMoney -= parseInt(totalMoney / bitcoinValue) * bitcoinValue;
        }
    }

    console.log(`Bought bitcoins: ${bitcoinCounter}`);
    if (firstDay !== undefined) {
        console.log(`Day of the first purchased bitcoin: ${firstDay}`);
    }
    console.log(`Left money: ${totalMoney.toFixed(2)} lv.`);
}

// mining([100,200,300]);
mining([3124.15, 504.212, 2511.124]);