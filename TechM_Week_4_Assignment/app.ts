interface Movie {
	title: string;
	image: string;
	description: string;
	rating: number;
}

document.addEventListener("DOMContentLoaded", () => {
	loadMovies();
});

// Fetch and display movies
async function loadMovies(): Promise<void> {
	try {
		const response = await fetch("movies.json");
		const movies: Movie[] = await response.json();
		displayMovies(movies);
	} catch (error) {
		console.error("Error loading movies:", error);
	}
}

// Display movies in UI
function displayMovies(movies: Movie[]): void {
	const movieList = document.getElementById("movieList") as HTMLElement;
	movieList.innerHTML = "";

	movies.forEach((movie) => {
		const movieCard = document.createElement("div");
		movieCard.classList.add("col-md-4", "mb-4");
		movieCard.innerHTML = `
            <div class="card">
                <img src="${movie.image}" class="card-img-top" alt="${movie.title}">
                <div class="card-body">
                    <h5 class="card-title">${movie.title}</h5>
                    <p class="card-text">${movie.description}</p>
                    <p class="text-warning">⭐ ${movie.rating}</p>
                </div>
            </div>
        `;
		movieList.appendChild(movieCard);
	});
}
