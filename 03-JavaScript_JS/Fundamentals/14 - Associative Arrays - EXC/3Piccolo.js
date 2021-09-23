function solve(input = []) {


    function fillParkingInfo(input) {
        let parking = {};
        input.forEach(line => {
            let car = line.split(", ");
            parking[car[1]] = car[0];

        });

        return parking;
    }

    function filterCarsIn(parking) {
        let output = [];
        Object.entries(parking).forEach(entry => {
            if (entry[1] === 'IN') {
                output.push(entry[0]);
            }
        })

        return output;
    }

    function filterCarsOut(arr = []) {
        let a = 5;
        return arr.length === 0 ? "Parking Lot is Empty"
            : arr.sort((f, s) => f.localeCompare(s)).join("\n");
    }

    let parkingS = fillParkingInfo(input);
    let output = filterCarsIn(parkingS);

    return filterCarsOut(output);
}

console.log(
    solve(['IN, CA2844AA',
        'IN, CA1234TA',
        'OUT, CA2844AA',
        'IN, CA9999TT',
        'IN, CA2866HI',
        'OUT, CA1234TA',
        'IN, CA2844AA',
        'OUT, CA2866HI',
        'IN, CA9876HH',
        'IN, CA2822UU']
    ));