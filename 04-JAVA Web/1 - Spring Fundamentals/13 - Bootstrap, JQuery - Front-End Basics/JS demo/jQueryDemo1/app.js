const traverse = (node) => {
    console.log(node);
    for (let child of node.children) {
        traverse(child);
    }
}

// traverse(node);

// document.getElementById("btn-add")
//     .addEventListener('click', () => {
//         const listNode = document.createElement('li');
//         listNode.innerHTML = 'NEW tehcnology';
//         document.getElementById('technologies-list')
//             .appendChild(listNode);
//     });


$('#btn-add')
    .on('click', () => {
        $('#technologies-list')
            .append($('<li>New Technology with jQuery</li>'));
    });

$('#technologies-list')
    .on('click', 'li', function () {
        $(this).remove();
    });




