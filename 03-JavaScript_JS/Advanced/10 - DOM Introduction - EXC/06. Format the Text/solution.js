function solve() {
  const text = document.getElementById("input").value;
  const splittedText = text.split(".").filter(el => el != "");
  const output = document.getElementById("output");
  let finalString = "";
  let result = "";

  for (let i = 0; i < splittedText.length; i++) {
    result += splittedText[i] + ".";

    if ((i+1) % 3 == 0 || i == splittedText.length -1) {
      result = "<p>" + result + "</p>";
      finalString += result;
      result = "";
    }    
  }
  
  output.innerHTML = finalString;
}