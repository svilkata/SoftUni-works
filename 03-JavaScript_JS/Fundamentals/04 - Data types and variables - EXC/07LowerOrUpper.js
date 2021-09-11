function upperOrLower(letter) {
    let l = letter;
    if (letter.charCodeAt(0) >= 65 && letter.charCodeAt(0) <= 90) {
        console.log('upper-case');
    } else if (letter.charCodeAt(0) >= 97 && letter.charCodeAt(0) <= 122) {
        console.log('lower-case');
    } else {
        console.log('Not a letter');
    }
}

upperOrLower('L');
upperOrLower('z');
upperOrLower('_');