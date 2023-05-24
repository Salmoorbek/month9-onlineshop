<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<div class="navbar">
    <ul>
        <li><a href="index">Home</a></li>
        <li><a href="product">Cars</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
    </ul>
    <div class="navbar-icons">
        <span id="userEmail">${userEmail}</span>
        <a href="cart"><i class="fas fa-shopping-cart"></i></a>
        <a href="login"><i class="fas fa-sign-in-alt"></i></a>
    </div>
</div>
<h1>Shopping Cart</h1>
<h2>Cart Items</h2>
<table>
    <thead>
    <tr>
        <th>Product Image</th>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <#if cartItems?has_content>
        <#list cartItems as cartItem>
            <tr>
                <td style="vertical-align: middle;">
                    <#list products as product>
                        <#if product.id == cartItem.productId>
                            <img src="${product.imageUrl}" alt="${product.name}" style="width: 100px; height: 100px;">
                        </#if>
                    </#list>
                </td>
                <td style="vertical-align: middle;">
                    <#list products as product>
                        <#if product.id == cartItem.productId>
                            ${product.name}
                        </#if>
                    </#list>
                </td>
                <td style="vertical-align: middle;">
                    <input type="number" class="quantity-input" value="${cartItem.quantity}" data-cart-item-id="${cartItem.id}">
                </td>
                <td style="vertical-align: middle;">${cartItem.price}</td>
                <td style="vertical-align: middle;">
                    <button onclick="removeFromCart('${cartItem.id}')">Удалить</button>
                </td>
            </tr>
        </#list>
        <form onsubmit="event.preventDefault(); createOrder();" method="post">
            <input type="submit" value="Оформить заказ">
        </form>
    <#else>
        <tr>
            <td colspan="5" style="text-align: center;">В корзине нет товаров</td>
        </tr>
    </#if>
    </tbody>
</table>
<script src="/js/cart.js"></script>
</body>
</html>
