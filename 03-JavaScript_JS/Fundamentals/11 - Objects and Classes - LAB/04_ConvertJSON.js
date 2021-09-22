function fromJStoJSON (inputJS1, inputJS2, inputJS3) {
    let objectJS = {
        name : inputJS1,
        lastName : inputJS2,
        hairColor : inputJS3,
    }
    let resultJSON = JSON.stringify(objectJS);
    console.log(resultJSON);
}

fromJStoJSON('George', 'Jones', 'Brown');