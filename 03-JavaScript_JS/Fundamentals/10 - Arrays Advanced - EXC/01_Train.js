function solve(input = []) {
    let wagons = input.shift().split(' ').map(x => Number(x));
    let maxCap = Number(input.shift());

    for (let i = 0; i < input.length; i++) {
        let command = input[i].split(' ');
        if (command.length === 2) {
            wagons.push(parseInt(command[1]));
        } else if (command.length === 1) {
            let peopleToEnterInOneWagon = Number(command[0]);
            for (let j = 0; j < wagons.length; j++) {
                if (peopleToEnterInOneWagon + wagons[j] <= maxCap) {
                    wagons[j] += peopleToEnterInOneWagon;
                    break;
                }                
            }
        }        
    }

    return wagons.join(" ");
}

// console.log(
//     solve(['32 54 21 12 4 0 23',
//         '75',
//         'Add 10',
//         'Add 0',
//         '30',
//         '10',
//         '75']
//     )
// );

console.log(
    solve(['0 0 0 10 2 4',
        '10',
        'Add 10',
        '10',
        '10',
        '10',
        '8',
        '6']

    )
);