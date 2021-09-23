function solve(input = []) {
    let map = {
        2: 2, 3: 3, 4: 4, 5: 5, 6: 6, 7: 7, 8: 8, 9: 9, 10: 10,
        J: 11, Q: 12, K: 13, A: 14, S: 4, H: 3, D: 2, C: 1
    };

    function createPlayers(input) {
        let players = {};
        input.forEach(line => {
            let tokens = line.split(": ");
            let name = tokens[0];
            let deck = tokens[1].split(", ");
            if (players[name] === undefined) {
                players[name] = new Set();
            }
            deck.forEach(card => {
                players[name].add(card);
            });
        });

        return players;
    }

    function sumDeck(arr, map) {
        let sum = 0;
        arr.forEach(card => {
            let tokens = card.split("");
            let type = tokens.pop();
            let power = tokens.join("");
            sum += map[power] * map[type];
        });

        return sum;
    }

    function createPlayersScore(players, map) {
        let obj = {};
        Object.entries(players).forEach(entry => {
            obj[entry[0]] = sumDeck(Array.from(entry[1].values()), map);
        });

        return obj;
    }



    let players = createPlayers(input);
    let scoreObj = createPlayersScore(players, map);
    Object.keys(scoreObj).forEach(key => {
        console.log(`${key}: ${scoreObj[key]}`);
    })


}

console.log(
    solve([
        'Peter: 2C, 4H, 9H, AS, QS',
        'Tomas: 3H, 10S, JC, KD, 5S, 10S',
        'Andrea: QH, QC, QS, QD',
        'Tomas: 6H, 7S, KC, KD, 5S, 10C',
        'Andrea: QH, QC, JS, JD, JC',
        'Peter: JD, JD, JD, JD, JD, JD'
    ]
    ));