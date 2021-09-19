function solve(input = []) {
    let output = input.slice().sort((a, b) => {
        if (a.length === b.length) {
            return a.localeCompare(b);
        } else {
            return a.length - b.length;
        }
    });

    console.log(output.join("\n"));
}

solve(["alpha", "beta", "gamma"]);
