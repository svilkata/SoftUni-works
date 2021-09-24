function sorted(arr = []) {
    arr.sort((a, b) => a.localeCompare(b));

    let i = 1;
    for (let el of arr) {
        console.log(i + "." + el);
        i++;
    }


}


sorted(["John", "Bob", "Christina", "Ema"]);
sorted(["John", "Bob", "Christina", "Ema"]);


