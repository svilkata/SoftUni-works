let button = document.querySelector("#load");
button.addEventListener('click', function loadRepos() {
    let url = 'https://api.github.com/users/testnakov/repos';
    const httpRequest = new XMLHttpRequest();
    httpRequest.addEventListener('readystatechange', function () {
        if (httpRequest.readyState == 4 && httpRequest.status == 200) {
            document.getElementById("res").textContent = httpRequest.responseText;
        }
    });
    httpRequest.open("GET", url);
    httpRequest.send();
});


await Promise.all([fetch('v1'), fetch('v2'), fetch('v3')]);

const v1 = await fetch('v1');
const v2 = await fetch('v2');
const v3 = await fetch('v3');





