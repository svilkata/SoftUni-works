let text = 'Az sym bylgarche obicham ...bylgarche';
let replacedText; 

while (text.indexOf('bylgarche') >= 0) {
    text = text.replace('bylgarche', 'evropeiche'); //само едно съвпадение
}

console.log(text);



















