console.log('My requests...')
const tbody = document.querySelector('tbody');
tbody.addEventListener('click', onTableClick);

function onTableClick(event) {
    if (event.target.className == 'delete') {
        console.log('clicked delete button');
    } else if (event.target.className == 'edit') {
        console.log('clicked edit button');
    }
}