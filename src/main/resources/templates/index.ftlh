<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/style.css">
</head>
<body>
<h1>Product Search</h1>
<form>
    <label for="search-name">Name:</label>
    <input type="text" id="search-name" name="name">
    <label for="search-description">Description:</label>
    <input type="text" id="search-description" name="description">
    <label for="search-price">Price:</label>
    <input type="number" id="search-price" name="price">
    <button type="submit">Search</button>
</form>
<div id="products-container"></div>
<div id="pagination-container" class="pagination"></div>
<script src="/main.js"></script>
</body>
</html>