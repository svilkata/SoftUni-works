class Song {
    constructor(typeList, name, time){
        this.typeList = typeList;
        this.name = name;
        this.time = time;
    }
}

function solve(...input) {
    let n = input.shift();
    let songs = [];
    for (let i = 0; i < n; i++) {
        let currentInput = input.shift();
        let [currentTypeList, currentName, currentTime] = currentInput.split("_");
        
        let song = new Song(currentTypeList, currentName, currentTime);
        songs.push(song);
    }

    let wantedList = input.shift();
    if (wantedList === 'all') {
        songs.forEach(x => console.log(x.name));
    } else {
        songs.forEach(x => {
            if (x.typeList === wantedList) {
                console.log(x.name);
            }
        });
    }
}



solve(3,
    'favourite_DownTown_3:14',
    'favourite_Kiss_4:16',
    'favourite_Smooth Criminal_4:01',
    'favourite'
    );

solve(2,
    'like_Replay_3:15',
    'ban_Photoshop_3:48',
    'all'
    );