function town(cityName, population, area) {
    let result = '';

    if (typeof (cityName) === 'string' && typeof (population) === 'number'
        && typeof (area) === 'number') {
        result = `Town ${cityName} has population of ${population} and area ${area} square km.`;
    } else {
        cityName = cityName.toString();
        population = Number(population);
        area = Number(area);
        if (isNaN(population) || isNaN(area)) {
            result = 'Invalid input';
        } else {
            result = `Town ${cityName} has population of ${population} and area ${area} square km.`;
        }

    }

    console.log(result);
}

town('Sofia',
    '111',
    492
);