function deleteByEmail() {
    //select input field and read value
    const input = document.querySelector("input[name='email']");

    //get tbody children
    const rows = Array.from(document.querySelector("tbody").children);


    let removed = false;
    //repeat for every row
    // --- select 2nd cell
    // --- if text content matches input value, remove row
    for (const row of rows) {
        if (row.children[1].textContent == input.value) {
            row.remove();
            removed = true;
        }
    }

    document.querySelector("input[name='email']").value = "";


    //if there is a match print "Deleted"
    //otherwise, print "Not found"
    if (removed) {
        document.getElementById("result").textContent = "Deleted.";
    } else {
        document.getElementById("result").textContent = "Not found.";
    }
}