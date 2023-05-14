const resultsPerPage = 10;
let currentPage = 0;
let products = [];

function getProducts(page) {
    const searchName = document.getElementById('search-name').value;
    const searchDescription = document.getElementById('search-description').value;
    const searchPrice = document.getElementById('search-price').value;
    const params = new URLSearchParams({
        page,
        size: resultsPerPage,
        name: searchName,
        description: searchDescription,
        price: searchPrice
    });
    fetch(`/product/all?${params}`)
        .then(response => response.json())
        .then(data => {
            products = data.content;
            const totalPages = data.totalPages;
            currentPage = data.number;
            renderProducts();
            renderPagination(totalPages);
        })
        .catch(error => console.error(error));
}
function renderProducts() {
    const productsContainer = document.getElementById('products-container');
    productsContainer.innerHTML = '';
    products.forEach(product => {
        const productCard = document.createElement('div');
        productCard.className = 'product-card';
        const productName = document.createElement('h2');
        productName.textContent = product.name;
        productCard.appendChild(productName);
        const productImage = document.createElement('img');
        productImage.src = product.imageUrl;
        productCard.appendChild(productImage);
        const productDescription = document.createElement('p');
        productDescription.textContent = product.description;
        productCard.appendChild(productDescription);
        const productPrice = document.createElement('p');
        productPrice.textContent = `Price: ${product.price}`;
        productCard.appendChild(productPrice);
        productsContainer.appendChild(productCard);
    });
}

function renderPagination(totalPages) {
    const paginationContainer = document.getElementById('pagination-container');
    paginationContainer.innerHTML = '';
    for (let i = 0; i < totalPages; i++) {
        const pageLink = document.createElement('a');
        pageLink.href = '#';
        pageLink.textContent = i + 1;
        pageLink.onclick = () => getProducts(i);
        if (i === currentPage) {
            pageLink.className = 'active';
        }
        paginationContainer.appendChild(pageLink);
    }
}

getProducts(0);
