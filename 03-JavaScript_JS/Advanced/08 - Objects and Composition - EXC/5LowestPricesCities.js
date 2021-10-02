function solve(arr = []) {
    let catalogue = {};
    arr.forEach(el => {
        let [town, product, price] = el.split(" | ");
        price = Number(price);
        if (!catalogue.hasOwnProperty(product)) {
            catalogue[product] = {};

        }
        catalogue[product][town] = price;    //презаписва се стойността           
    })

    for (const prod in catalogue) {
       const sortedTownPrice = Object.entries(catalogue[prod]).sort((f, s) => f[1] - s[1]);
       console.log(`${prod} -> ${sortedTownPrice[0][1]} (${sortedTownPrice[0][0]})`);
    }
}

console.log(
    solve(['Sample Town | Sample Product | 1000',
        'Sample Town | Orange | 1',
        'Sample Town | Orange | 5',
        'Sofia | Orange | 3',
        'Sofia | Peach | 2',
        'New York | Sample Product | 1000.1',
        'New York | Burger | 10']
    ));

