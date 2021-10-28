function validate() {
    const userNameRegexPattern = /(^[a-zA-z0-9]{3,20}$)/g;
    const passwordRegexPattern = /(^[\w]{5,15}$)/g;
    const emailRegexPattern = /(^\w+@\w+\.\w+)/g;
    let isValidInput = true;
    let isCheckboxChecked = false;


    const submitButton = document.getElementById("submit");
    submitButton.addEventListener('click', (ev) => {
        ev.preventDefault();

        //Проверка име
        const userNameField = document.querySelector("#username");
        if (!userNameRegexPattern.test(userNameField.value)) {
            isValidInput = false;
            userNameField.style.borderColor = "red";
        } else {
            userNameField.style.border = "none";
        }

        //проверка парола
        const passField = document.querySelector("#password");
        const confirmPassField = document.querySelector("#confirm-password");
        if (passField.value != confirmPassField.value) {
            isValidInput = false;
            passField.style.borderColor = "red";
            confirmPassField.style.borderColor = "red";
        } else {
            if (!passwordRegexPattern.test(passField.value)) {
                isValidInput = false;
                passField.style.borderColor = "red";
                confirmPassField.style.borderColor = "red";
            } else {
                passField.style.border = "none";
                confirmPassField.style.border = "none";
            }
        }

        //проверка е-мейл
        const emailField = document.getElementById("email");
        if (!emailRegexPattern.test(emailField.value)) {
            isValidInput = false;
            emailField.style.borderColor = "red";
        } else {
            emailField.style.border = "none";
        }

        if (isCheckboxChecked) {
            const companyNumber = document.getElementById("companyNumber");
            if (Number(companyNumber.value) < 1000 || Number(companyNumber.value) > 9999) {
                companyNumber.style.borderColor = "red";
                isValidInput = false;
            } else {
                companyNumber.style.border = "none";
            }
        }

        const validDiv = document.getElementById("valid");
        if (isValidInput) {
            validDiv.style.display = "block";
        } else {
            validDiv.style.display = "none";
        }


    });

    document.getElementById("company").addEventListener("change", (ev) => {
        const companyField = document.getElementById("companyInfo");
        if (ev.target.checked) {
            isCheckboxChecked = true;
            companyField.style.display = "block";
        } else {
            isCheckboxChecked = false;
            companyField.style.display = "none";
        }
    });

}
