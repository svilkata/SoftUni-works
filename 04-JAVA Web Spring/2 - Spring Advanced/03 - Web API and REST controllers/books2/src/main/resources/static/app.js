let loadBooksBtn = document.getElementById("loadBooks");
loadBooksBtn.addEventListener('click', onLoadBooks);

function onLoadBooks(event) {
    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    const authorsContainer = document.getElementById('authors-container');
    authorsContainer.innerHTML = '';

    fetch("http://localhost:8080/api/books", requestOptions)
        .then(response => response.json())
        .then(result => result.forEach(book => {
            //here we will create some elements and add them to the table
            const row = document.createElement('tr');

            const titleCol = document.createElement('td');
            const authCol = document.createElement('td');
            const isbnCol = document.createElement('td');
            const actionCol = document.createElement('td');

            titleCol.textContent = book.title;
            authCol.textContent = book.authorDTO.name;
            isbnCol.textContent = book.isbn;
            actionCol.innerHTML = '<a id="edit" class="button">Edit</a>\n' +
                '<a id="del" class="button" href=`http://localhost:8080/api/books/${book.id}`>Delete</a>';

            //add columns to the parent row
            row.appendChild(titleCol);
            row.appendChild(authCol);
            row.appendChild(isbnCol);
            row.appendChild(actionCol);

            authorsContainer.appendChild(row);
        }))
        .catch(error => console.log('error', error));
}

















