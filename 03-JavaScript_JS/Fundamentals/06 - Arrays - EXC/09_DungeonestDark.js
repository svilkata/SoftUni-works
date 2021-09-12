function solve(data = []) {
    let bestRoom = 0;
    let health = 100;
    let coins = 0;

    let dungeon = data[0].split('|');

    for (let room of dungeon) {
        let tokens = room.split(' ');
        let encountered = tokens[0];
        let value = Number(tokens[1]);
        if (encountered === 'potion') {
            if (health + value > 100) {       
                console.log(`You healed for ${100 - health} hp.`);
                health = 100;
            } else {
                console.log(`You healed for ${value} hp.`);
                health+= value;
            }
            console.log(`Current health: ${health} hp.`); 
            bestRoom++;          
        } else if (encountered === 'chest') {
            coins+= value;
            console.log(`You found ${value} coins.`);
            bestRoom++;
        } else {
            //We fight here
            bestRoom++;
            health-= value;
            if (health <= 0) {
                console.log(`You died! Killed by ${encountered}.`);                
                console.log(`Best room: ${bestRoom}`);
                break;
            } else {
                console.log(`You slayed ${encountered}.`);
            }
        }
    }

    if (dungeon.length === bestRoom) {
        console.log("You've made it!");
        console.log(`Coins: ${coins}`); 
        console.log(`Health: ${health}`); 
    }    
}

solve(['rat 10|bat 20|potion 10|rat 10|chest 100|boss 70|chest 1000']);
// solve(['cat 10|potion 30|orc 10|chest 10|snake 25|chest 110']);

