<!DOCTYPE html>
<html>
<head>
    <title>Order Items</title>
    <link rel="stylesheet" href="/css/product.css">
</head>
<body>
<div class="navbar">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="/product">Cars</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="/reviews">Comments</a></li>
        <li><a href="/orders">Orders</a></li>
    </ul>
    <div class="navbar-icons">
        <span id="userEmail">${userEmail}</span>
        <a href="carts"><i class="fas fa-shopping-cart"></i></a>
        <#if userEmail?has_content && userEmail != 'anonymousUser'>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button type="submit"><i class="bi bi-box-arrow-right"></i></button>
            </form>
        <#else>
            <a href="/login"><i class="bi bi-person-fill"></i></a>
        </#if>
    </div>
</div>
<h1>Order Items</h1>
<table>
    <tr>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Add Comment</th>
    </tr>
    <#list orderItems as orderItem>
        <tr>
            <td style="vertical-align: middle;">
                <#list products as product>
                    <#if product.id == orderItem.productId>
                        <img src="${product.imageUrl}" alt="${product.name}" style="width: 100px; height: 100px;">
                    </#if>
                </#list>
            </td>
            <td style="vertical-align: middle;">
                <#list products as product>
                    <#if product.id == orderItem.productId>
                        ${product.name}
                    </#if>
                </#list>
            </td>
            <td style="vertical-align: middle;">${orderItem.quantity}</td>
            <td style="vertical-align: middle;">${orderItem.price}</td>
        </tr>
    </#list>
</table>
</body>
</html>
