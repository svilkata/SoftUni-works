function matrix(num) {
    for (let i = 0; i < num; i++) {
        let lineString = '';
        for (let j = 0; j < num - 1; j++) {
            lineString+= num + ' ';            
        }
        lineString += num;
        console.log(lineString);        
    }
}

matrix(5);