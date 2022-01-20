function loadRepos() {
	const username = document.getElementById('username').value;
	const url = `https://api.github.com/users/${username}/repos`;
	const list = document.getElementById('repos');

	fetch(url)
		.then(res => {
			if (res.ok == false) {
				throw new Error(`${res.status} ${res.statusText}`);
			}

			return res.json();
		})
		.then(handleResponse)
		.catch(handleError);

	function handleResponse(data) {
		list.innerHTML = '';

		for(let repo of data){
			const liElement = document.createElement('li');
			liElement.innerHTML = `<a href="${repo.html_url}">
			${repo.full_name}
		</a>`

			// console.log(repo.full_name, repo.html_url);
			list.appendChild(liElement);
		}
	}

	function handleError(error) {
		list.innerHTML = '';
		list.textContent = `${error.message}`;
	}
}