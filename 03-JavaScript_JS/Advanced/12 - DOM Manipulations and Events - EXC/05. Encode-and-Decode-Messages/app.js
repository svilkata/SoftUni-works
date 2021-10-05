function encodeAndDecodeMessages() {
    const el1 = document.querySelector('textarea[placeholder="Write your message here..."]');
    let origText = "";
    const el2 = document.querySelector('textarea[placeholder="No messages..."]');
    const buttons = document.querySelectorAll('button');
    const btn1 = buttons[0];
    btn1.addEventListener('click', onClick1);

    const btn2 = buttons[1];
    // console.log(btn1, btn2);
    // console.log(el1, el2);
    btn2.addEventListener('click', onClick2);


    function onClick1(e) {
        const areaText = e.target.parentElement.querySelector('textarea[placeholder="Write your message here..."]').value;
        let encodedText = "";
        for (let i = 0; i < areaText.length; i++) {
            encodedText += String.fromCharCode(areaText[i].charCodeAt(0) + 1);
        }
        origText = areaText;
        el1.value = "";
        el2.value = encodedText;
    }

    function onClick2(e) {
        // let decodingText = e.target.parentElement.querySelector('textarea[placeholder="No messages..."]').value;
        // decodingText = origText;

        el2.value = origText;
        // console.log(origText);

        //  console.log(decodingText);

    }

}