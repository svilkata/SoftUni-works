function validate() {
    document.getElementById("email").addEventListener('change', onChange); //излизаме от input-a и натискаме с мишката извън полето за въвеждане

    function onChange({ target }) { //деструктуриране
        console.log(target.value);
        const pattern = /^[a-z]+@[a-z]+\.[a-z]+$/;

        if (pattern.test(target.value)) {
            target.classList.remove('error');
        } else {
            target.classList.add('error');
        }
    }
}