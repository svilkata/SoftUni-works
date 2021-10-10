function solve() {
    const [name, hall, ticketPrice] = document.querySelectorAll("#container input");
    const movieSection = document.querySelector("#movies ul");
    const archiveSection = document.querySelector("#archive ul");
    const addMovieButton = document.querySelector("#container button");
    addMovieButton.addEventListener('click', addMovie);

    const buttonClear = archiveSection.parentElement.querySelector("button");
    buttonClear.addEventListener("click", () => {
        archiveSection.parentElement.querySelector("ul").textContent = "";
    });


    function addMovie(ev) {
        ev.preventDefault();
        if (name.value !== '' && hall.value !== '' && !isNaN(Number(ticketPrice.value))) {
            const movie = document.createElement("li");
            movie.innerHTML = `<span>${name.value}</span>
            <strong>${hall.value}</strong>
            <div>
                <strong>${Number(ticketPrice.value).toFixed(2)}</strong>
                <input placeholder="Tickets sold">
                <button>Archive</button>                
            </div>`;

            name.value = "";
            hall.value = "";
            ticketPrice.value = "";

            movieSection.appendChild(movie);
            const button = movie.querySelector("div button");
            button.addEventListener("click", addToArchive);
        }
    }

    function addToArchive(e) {
        const inputValue = e.target.parentElement.querySelector("input");
        const ticketPrice = e.target.parentElement.querySelector("strong");
        const movieName = e.target.parentElement.parentElement.querySelector("span");
        if (inputValue.value !== '' && !isNaN(Number(inputValue.value))) {
            const income = Number(inputValue.value) * Number(ticketPrice.textContent);

            const liEl = document.createElement("li");
            liEl.innerHTML = `<span>${movieName.textContent}</span>
            <strong>Total amount: ${income.toFixed(2)}</strong>
            <button>Delete</button>`;

            archiveSection.appendChild(liEl);
            e.target.parentElement.parentElement.remove();

            const buttonDelete = liEl.querySelector("button");
            buttonDelete.addEventListener("click", deleteEntry);
        }
    }

    function deleteEntry(e) {
        const elToDelete = e.target.parentElement.remove();
    }
}