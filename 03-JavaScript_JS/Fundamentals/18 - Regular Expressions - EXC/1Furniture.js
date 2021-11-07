function solve(input) {
    const pattern = /^>>([a-zA-Z]+)<<([0-9]+\.?[0-9]*)!(\d+)$/gm;
    let lines = input
        .slice(0, input.indexOf('Purchase'))
        .join("\n");

    let arr = Array.from(lines.matchAll(pattern));
    let names = `Bought furniture:`;
    let cost = 0;

    arr.forEach(match => {
        // console.log(match);
        names += `\n` + match[1];
        cost += Number(match[2]) * Number(match[3]);
    });


    names += '\n' + `Total money spend: ${cost.toFixed(2)}`;

    return names;
}


console.log(
    solve([">>Sofa<<312.23!3",
        ">>TV<<300!5",
        ">Invalid<<!5",
        "Purchase"]
    ));