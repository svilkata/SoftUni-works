function solve(input) {
    let totalPrice = 0;
    let output = "";

    input.forEach(line => {
        let pattern = /^%([A-Z][a-z]+)%(?:[^\|\$\%\.]*)<(\w+)>(?:[^\|\$\%\.]*)\|(\d+)\|\D*(\d+\.?\d*)\$$/gm;
        let element = pattern.exec(line);
        // console.log(element);
        if (element != null) {
            let price = Number(element[3]) * Number(element[4]);
            totalPrice += price;
            output += `${element[1]}: ${element[2]} - ${price.toFixed(2)}\n`;
        }
    })

    output += `Total income: ${totalPrice.toFixed(2)}`;

    return output;

}

// console.log(solve(
//     [
//         "%George%<Croissant>|2|10.3$",
//         "%Peter%<Gum>|1|1.3$",
//         "%Maria%<Cola>|1|2.4$",
//         "end of shift"
//     ]
// ));

console.log(solve(
    [
        "%InvalidName%<Croissant>|2|10.3$",
        "%Peter%<Gum>1.3$",
        "%Maria%<Cola>|1|2.4",
        "%Valid%<Valid>valid|10|valid20$",
        "end of shift"
    ]
));