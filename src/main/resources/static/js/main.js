const resultsPerPage = 10;
let currentPage = 0;
let cars = [];

function getCars(page) {
    const searchMake = document.getElementById('search-term').value;
    const searchModel = document.getElementById('search-term').value;
    const searchPrice = document.getElementById('search-term').value;
    const params = new URLSearchParams({
        page,
        size: resultsPerPage,
        make: searchMake,
        model: searchModel,
        price: searchPrice
    });
    fetch(`/product/all?${params}`)
        .then(response => response.json())
        .then(data => {
            cars = data.content;
            const totalPages = data.totalPages;
            currentPage = data.number;
            renderCars();
            renderPagination(totalPages);
        })
        .catch(error => console.error(error));
}

function renderCars() {
    const carsContainer = document.querySelector('.cars-block');
    carsContainer.innerHTML = '';
    cars.forEach(car => {
        const carCard = document.createElement('div');
        carCard.className = 'car-card';
        const carMakeModel = document.createElement('h2');
        carMakeModel.textContent = `Name: ${car.name}`;
        carCard.appendChild(carMakeModel);
        const carImage = document.createElement('img');
        carImage.src = car.imageUrl;
        carCard.appendChild(carImage);
        const carDescription = document.createElement('p');
        carDescription.textContent = `Description: ${car.description}`;
        carCard.appendChild(carDescription);
        const carPrice = document.createElement('p');
        carPrice.textContent = `Price: $${car.price}`;
        carCard.appendChild(carPrice);
        carsContainer.appendChild(carCard);
    });
}

function renderPagination(totalPages) {
    const paginationContainer = document.querySelector('.pagination');
    paginationContainer.innerHTML = '';
    for (let i = 0; i < totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.href = '#';
        pageLink.textContent = i + 1;
        pageLink.onclick = () => getCars(i);
        if (i === currentPage) {
            pageLink.className = 'active';
        }
        paginationContainer.appendChild(pageLink);
    }
}

getCars(0);
