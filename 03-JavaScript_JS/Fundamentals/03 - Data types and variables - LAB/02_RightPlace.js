// function rightPlace(firstWord, character, secondWord) {
//     let matchWord = '';

//     for (let i = 0; i < firstWord.length; i++) {
//         if (firstWord[i] === '_') {
//             matchWord += character;
//         } else {
//             matchWord += firstWord[i];
//         }
//     }

//     if (matchWord === secondWord) {
//         console.log("Matched");
//     } else {
//         console.log("Not Matched");
//     }
// }

function rightPlaceWithReplace(firstWord, character, secondWord) {
    console.log(arguments);

    let matchWord = firstWord.replace('_', character);    

    if (matchWord === secondWord) {
        console.log("Matched");
    } else {
        console.log("Not Matched");
    }
}

// rightPlace('Str_ng', 'I', 'Strong');
// rightPlace('Str_ng', 'i', 'String');

rightPlaceWithReplace('Str_ng', 'I', 'Strong', 'zzzz');
// rightPlaceWithReplace('Str_ng', 'i', 'String');