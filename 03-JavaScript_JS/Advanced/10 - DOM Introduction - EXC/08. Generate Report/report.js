function generateReport() {
    // const input = document.getElementsByTagName("th");
    const checkedElements = document.querySelectorAll("input");
    const rowsAll = document.querySelectorAll("tbody tr");
    let result = [];

    if (checkedElements.length < 1) {
        return;
    }

    for (let i = 0; i < rowsAll.length; i++) {
        let newObj = {};
        for (let y = 0; y < checkedElements.length; y++) {
            if (checkedElements[y].checked) {
                newObj[checkedElements[y].name] = rowsAll[i].children[y].textContent;
            }            
        }

        result.push(newObj);        
    }

    document.getElementById("output").textContent = JSON.stringify(result);    
}