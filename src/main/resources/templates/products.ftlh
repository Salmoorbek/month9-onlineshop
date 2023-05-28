<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Car List</title>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link rel="stylesheet" href="/css/product.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<div class="navbar">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="#">Cars</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="#">About</a></li>
        <li><a href="/orders">Orders</a></li>
    </ul>
    <div class="navbar-icons">
        <span id="userEmail">${userEmail}</span>
        <a href="/carts"><i class="fas fa-shopping-cart"></i></a>
        <#if userEmail?has_content && userEmail != 'anonymousUser'>
            <form action="/logout" method="post">
                <input type="hidden" name="${(_csrf.parameterName)!'_csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                <button type="submit"><i class="bi bi-box-arrow-right"></i></button>
            </form>
        <#else>
            <a href="login"><i class="bi bi-person-fill"></i></a>
        </#if>
    </div>
</div>

<h1>Car List</h1>
<div class="search-form">
    <form id="searchForm" method="get">
        <label for="searchCategory">Search by:</label>
        <select id="searchCategory">
            <option value="name">Name</option>
            <option value="price">Price</option>
            <option value="description">Description</option>
            <option value="category">Category</option>
        </select>
        <div id="priceInputs" style="display: none;">
            <input type="text" id="minPrice" name="minPrice" placeholder="Min Price">
            <input type="text" id="maxPrice" name="maxPrice" placeholder="Max Price">
        </div>
        <input type="text" id="searchValue" name="value">
        <button type="submit">Search</button>
    </form>
</div>


<table>
    <tbody>
    <#list products.content as product>
        <td>
            <div class="product-image">
                <img src="${product.imageUrl}" alt="Product Image">
            </div>
            <div class="product-info" data-product-id="123">
                <div>${product.name}</div>
                <div>${product.price}</div>
                <div>${product.category}</div>
                <button class="add-to-cart-button" data-product-id="${product.id}">Add to Cart</button>

            </div>
        </td>
        <#if (product_index + 1) % 3 == 0 || (product_index + 1) == products.content?size>
            </tr>
        </#if>
    </#list>
    </tbody>
</table>

<div class="pagination">
    <div>
        <#if products.hasPrevious()>
            <#assign previousPageable = products.previousPageable()>
            <a href="?page=${previousPageable.pageNumber}&size=${previousPageable.pageSize}">Previous</a>
        </#if>

        <#if products.hasNext()>
            <#assign nextPageable = products.nextPageable()>
            <a href="?page=${nextPageable.pageNumber}&size=${nextPageable.pageSize}">Next</a>
        </#if>
    </div>
</div>

<script src="/js/product.js"></script>
</body>
</html>