let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content");
document.getElementById("searchForm").addEventListener("submit", function (event) {
    event.preventDefault();
    var category = document.getElementById("searchCategory").value;
    var value = document.getElementById("searchValue").value;
    var minPrice = document.getElementById("minPrice").value;
    var maxPrice = document.getElementById("maxPrice").value;

    var params = {};
    if (category === "name") {
        params["name"] = value;
    } else if (category === "price") {
        if (minPrice !== "" && maxPrice !== "") {
            params["minPrice"] = minPrice;
            params["maxPrice"] = maxPrice;
        }
    } else if (category === "description") {
        params["description"] = value;
    } else if (category === "category") {
        params["category"] = value;
    }

    var queryString = Object.keys(params).map(function (key) {
        return key + "=" + encodeURIComponent(params[key]);
    }).join("&");

    var url = "/product/" + category;
    if (queryString !== "") {
        url += "?" + queryString;
    }
    window.location.href = url;
});
document.getElementById("searchCategory").addEventListener("change", function (event) {
    var selectedCategory = event.target.value;
    var priceInputs = document.getElementById("priceInputs");

    if (selectedCategory === "price") {
        priceInputs.style.display = "block";
    } else {
        priceInputs.style.display = "none";
    }
});


const addToCartButtons = document.querySelectorAll('.add-to-cart-button');
addToCartButtons.forEach(button => {
    button.addEventListener('click', function (event) {
        event.preventDefault();
        const productId = this.getAttribute('data-product-id');
        addToCart(productId);
    });
});

function addToCart(productId) {
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    fetch("/carts/carts/items/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            [csrfHeader]: csrfToken
        },
        body: `productId=${productId}`
    })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                console.error("Failed to add product to cart");
            }
        })
        .catch(error => {
            console.error("Failed to add product to cart", error);
        });
}

