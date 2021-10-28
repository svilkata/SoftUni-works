function validate() {
    const inputField = document.getElementById("email");
    const validateRegexPattern = /^([a-z]+@[a-z]+\.[a-z]+)$/g;

    inputField.addEventListener("change", () => {
       if (!validateRegexPattern.test(inputField.value)) {
           inputField.classList.add("error");
       } else {
        inputField.classList.remove("error");
       }
    });
}