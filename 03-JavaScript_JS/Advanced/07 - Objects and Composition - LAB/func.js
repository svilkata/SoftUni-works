function fromJSONToHTMLTable(input) {
    //Write your code here
    let arrObj = JSON.parse(input);
    let outputArr = ['<table>'];
    outputArr.push(makeKeyRow(arrObj));
    arrObj.forEach((obj) => outputArr.push(makeValueRow(obj)));
    outputArr.push('</table>');
    
    function makeKeyRow(arr) {
        let result = '  <tr>';
        Object.keys(arr[0]).forEach(key => {
            result += `<th>${escapeHtml(key)}</th>`
        });
        result += '</tr>';
        return result;
    }

    function makeValueRow(obj) {
        let result = '  <tr>';
        Object.values(obj).forEach(value => {
            result += `<td>${escapeHtml(value)}</td>`
        });
        result += '</tr>';
        return result;
    };

    function escapeHtml(value) {
        return value
            .toString()
            .replace(/&/g, '&amp;')
            .replace(/</g, '&lt;')
            .replace(/>/g, '&gt;')
            .replace(/"/g, '&quot;')
            .replace(/'/g, '&#39;')
    }

    console.log(outputArr.join("\n"));
    // return outputArr.join("\n");
}