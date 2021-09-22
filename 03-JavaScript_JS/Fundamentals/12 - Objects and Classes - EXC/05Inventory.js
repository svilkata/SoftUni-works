function solve(input = []) {
    let heroes = [];

    while (input.length > 0) {
        let line = input.shift().split(" / ");
        let hero = {
            name: line[0],
            level: Number(line[1]),
            items: line[2]
                .split(", ")
                .sort((f, s) => f.localeCompare(s))
                .join(", ")
        };

        heroes.push(hero);
    }

    heroes.sort((hero1, hero2) => {
        return hero1.level - hero2.level;
    });

    let output = [];
    for (const hero of heroes) {
        let str = `Hero: ${hero.name}\nlevel => ${hero.level}\nitems => ${hero.items}`;
        output.push(str);
    }

    return output.join("\n");
}

console.log(
    solve([
        "Isacc / 25 / Apple, GravityGun",
        "Derek / 12 / BarrelVest, DestructionSword",
        "Hes / 1 / Desolator, Sentinel, Antara"
    ]
    ));