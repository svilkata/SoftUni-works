function focused() {
    const fields = Array.from(document.getElementsByTagName("input"));

    for (const field of fields) {
        field.addEventListener('focus', onFocus);
        field.addEventListener('blur', onBlur);
    }

    function onFocus(ev) {
        ev.target.parentNode.classList.add('focused');
        console.log(ev.target);
    }

    function onBlur(ev) {
        ev.target.parentNode.classList.remove('focused');
        console.log(ev.target);
    }
}