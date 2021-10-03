function sumTable() {
    //select first table
    let rows = document.querySelectorAll('table tr');

    let sum = 0;

    //select only rows containing values
    // repeat for every row 
    // - find last cell
    // - add cell value to sum
    for (let i = 1; i < rows.length - 1; i++) {
        const cell = Number(rows[i].lastElementChild.textContent);
        sum += cell;
    }

    //select element with id "sum" and set its value
    document.getElementById('sum').textContent = sum;
}