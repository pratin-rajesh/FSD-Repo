document.addEventListener("DOMContentLoaded", () => {
	setupEventListeners();
});

// Function to set up event listeners
function setupEventListeners() {
	const searchInput = document.getElementById("searchInput");
	const reviewForm = document.getElementById("reviewForm");
	const contactForm = document.getElementById("contactForm");

	if (searchInput) searchInput.addEventListener("input", filterMovies);
	if (reviewForm) reviewForm.addEventListener("submit", submitReview);
	if (contactForm) contactForm.addEventListener("submit", sendMessage);
}

// Function to filter movies based on search input
function filterMovies() {
	const query = document.getElementById("searchInput").value.toLowerCase();
	const movieCards = document.querySelectorAll(".movies .card");

	movieCards.forEach((card) => {
		const title = card.querySelector(".card-title").textContent.toLowerCase();
		card.style.display = title.includes(query) ? "block" : "none";
	});
}

// Function to handle review submission
function submitReview(event) {
	event.preventDefault();

	const reviewMovieInput = document.getElementById("reviewMovie");
	const reviewTextInput = document.getElementById("reviewText");
	const reviewsList = document.getElementById("reviewsList");

	if (reviewMovieInput.value && reviewTextInput.value) {
		const reviewElement = document.createElement("p");
		reviewElement.innerHTML = `<strong>${reviewMovieInput.value}:</strong> ${reviewTextInput.value}`;
		reviewsList.appendChild(reviewElement);

		reviewMovieInput.value = "";
		reviewTextInput.value = "";
	} else {
		alert("Please fill in both fields before submitting.");
	}
}

// Function to handle contact form submission
function sendMessage(event) {
	event.preventDefault();

	const contactName = document.getElementById("contactName");
	const contactEmail = document.getElementById("contactEmail");
	const contactMessage = document.getElementById("contactMessage");

	if (contactName.value && contactEmail.value && contactMessage.value) {
		alert(`Thank you, ${contactName.value}! Your message has been sent.`);
		contactName.value = "";
		contactEmail.value = "";
		contactMessage.value = "";
	} else {
		alert("Please complete all fields before submitting.");
	}
}
