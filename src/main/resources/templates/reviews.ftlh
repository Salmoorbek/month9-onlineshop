<!DOCTYPE html>
<html>
<head>
    <title>Reviews</title>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<style>
    .product-image img {
        width: 370px;
        height: 300px;
        display: block;
        margin: 0 auto;
    }
    .product-name {
        text-align: center;
    }
    ul {
        padding: 0;
        margin: 0;
        list-style-type: none;
    }
</style>
<body>
<div class="navbar">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/product">Cars</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="#">About</a></li>
        <li><a href="/orders">Orders</a></li>
    </ul>
    <div class="navbar-icons">
        <span id="userEmail">${userEmail}</span>
        <a href="carts"><i class="fas fa-shopping-cart"></i></a>
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
<div class="container">
    <h3 class="text-center">Purchased Products:</h3>
    <div class="row">
        <#if products?has_content>
            <#list products as product>
                <div class="col-4 text-center">
                    <div class="product-image">
                        <img src="${product.imageUrl}" alt="Product Image">
                    </div>
                    <h4 class="product-name">${product.name}</h4>
                    <ul>
                        <h4 class="text-center">Reviews</h4>
                        <#list reviews as review>
                            <#if review.productId == product.id>
                                <li>Comments: ${review.text}</li>
                                <li>Rating: ${review.rating}</li>
                            </#if>
                        </#list>
                    </ul>
                    <div class="comment-form">
                        <form action="/reviews/create" method="post">
                            <input type="hidden" name="productId" value="${product.id}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="form-group">
                                <label for="rating">Rating:</label>
                                <select name="rating" id="rating" class="form-control">
                                    <option value="5">5 stars</option>
                                    <option value="4">4 stars</option>
                                    <option value="3">3 stars</option>
                                    <option value="2">2 stars</option>
                                    <option value="1">1 star</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="comment">Comment:</label>
                                <textarea name="comment" id="comment" rows="3" placeholder="Add a comment" class="form-control"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Comment</button>
                        </form>
                    </div>
                </div>
            </#list>
        <#else>
            <div class="col-12 text-center">
                <p>No purchased products found.</p>
            </div>
        </#if>
    </div>
</div>
</body>
</html>