function attachEvents() {
    //add event listener to load button
    document.getElementById('refresh').addEventListener('click', loadMessages);

    //add event listener to post button
    document.getElementById('submit').addEventListener('click', onSubmit);

    loadMessages();
}

const authorInput = document.querySelector('[name="author"]');
const contentInput = document.querySelector('[name="content"]');

attachEvents();

//load and display all messages
async function loadMessages() {
    const url = 'http://localhost:3030/jsonstore/messenger';
    const response = await fetch(url);
    const data = await response.json();

    const messages = Object.values(data);

    const list = document.getElementById('messages');
    list.value = messages.map(m => `${m.author}: ${m.content}`).join('\n');
}

//post message
async function createMessage(message) {
    const url = 'http://localhost:3030/jsonstore/messenger';
    const options = {
        method: 'post',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(message)
    };

    const response = await fetch(url, options);
    const result = await response.json();

    return result;
}


//add single message to list
async function onSubmit() {
    const author = authorInput.value;
    const content = contentInput.value;

    const result = await createMessage({author, content});

    contentInput.value = "";
    lit.value += '\n' + `${m.author}: ${m.content}`;
}