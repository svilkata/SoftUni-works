function toggle() {
    const button = document.querySelector(".button");
    const textDIV = document.getElementById("extra");

    button.textContent = button.textContent == "More" ? "Less" : "More";

    (textDIV.style.display == "none" || textDIV.style.display == "") ?
        textDIV.style.display = "block" : textDIV.style.display = "none";


    console.log(button);
}