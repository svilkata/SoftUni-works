function solve(input = []) {
    let neighborhoods = new Map();
    input.shift().split(', ').forEach(el => {
        neighborhoods.set(el, []);
    });

    for (const line of input) {
        let [neighborhoodName, person] = line.split(' - ');
        if (neighborhoods.has(neighborhoodName)) {
            neighborhoods
                .get(neighborhoodName)
                .push(person);
        }
    }

    // console.log(neighborhoods);
    let temp = Array.from(neighborhoods.entries());

    // console.log(temp);
    let a = 5;

    temp.sort((f, s) => {
        return s[1].length - f[1].length;
    })

    console.log(temp);

}

solve(['Abbey Street, Herald Street, Bright Mews',
    'Bright Mews - Garry',
    'Bright Mews - Andrea',
    'Invalid Street - Tommy',
    'Abbey Street - Billy']
);