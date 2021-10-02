function townPopulation(townsArr) {
    const towns = {};
    for (let townAsString of townsArr) {
        let [name, population] = townAsString.split(' <-> ');
        population = Number(population);
        if (towns[name] != undefined) {
            population += towns[name];
        }
        towns[name] = population;
    }

    for (let key in towns) {
        console.log(`${key} : ${towns[key]}`);
    }
}

townPopulation(['Istanbul <-> 100000',
    'Honk Kong <-> 2100004',
    'Jerusalem <-> 2352344',
    'Mexico City <-> 23401925',
    'Istanbul <-> 1000']
);
