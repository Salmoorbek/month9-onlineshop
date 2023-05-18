<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Car Dealership</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary bg-dark" data-bs-theme="dark">
    <div class="container">
        <a class="navbar-brand" href="#">Car Dealership</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <form class="d-flex" id="car-search-form">
                <select class="form-select pe-5" name="search-type" id="search-type">
                    <option value="all" selected>All</option>
                    <option value="name">Name</option>
                    <option value="price">Price</option>
                    <option value="year">Year</option>
                </select>
                <div class="input-group">
                    <input class="form-control search-input me-2 ms-2" type="search" placeholder="Search" aria-label="Search" name="search" id="search-term">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </div>
            </form>
        </div>
    </div>
</nav>

<div class="container my-4">
    <h1 class="text-center mb-4 mt-5 main-title">Available Cars</h1>

    <div class="cars-block row"></div>

    <div class="pagination">
        <ul>
            <li><a href="#" class="prev btn-primary">Prev</a></li>
            <li><a href="#" class="current-page active">0</a></li>
            <li><a href="#" class="next btn-primary">Next</a></li>
        </ul>
    </div>
</div>

<script src="/js/main.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>