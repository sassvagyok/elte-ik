const filter = document.querySelector("#filter");
const list = document.querySelector("#list");
const details = document.querySelector("#details");

filter.addEventListener("input", onFilter);
list.addEventListener("click", onClick);

function onFilter(e) {
    const filterText = filter.value.trim();

    const filterMovies = filterText === "" ? []
    : movies.filter(x => x.title.toLowerCase().includes(filterText.toLowerCase())).splice(0, 10);

    list.innerHTML = filterMovies.map(x => `<li data-id=${x.id}>${x.title}</li>`).join("");
}

function onClick(e) {
    if (e.target.matches("li")) {
        const id = Number(e.target.dataset.id);
        const movie = movies.find(x => x.id === id);

        details.innerHTML = `${movie.title}<br><br>${movie.director} (${movie.year})`;
    }
}