<!DOCTYPE html>
<html>
<head>
    <title>Order Items</title>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link rel="stylesheet" href="/css/product.css">
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
<h1>Order Items</h1>

<h2>Order ID: ${order.id}</h2>
<h3>User Email: ${userEmail}</h3>

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
            <td style="vertical-align: middle;">
                <form action="/comments" method="post">
                    <input type="hidden" name="product_id" value="${orderItem.productId}">
                    <input type="text" name="comments" placeholder="Enter your comment">
                    <input type="number" name="rating" placeholder="Enter your rating" min="1" max="5">
                    <input type="submit" value="Add Comment">
                </form>
            </td>
        </tr>
    </#list>
</table>
<script>
    var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    document.getElementById("commentForm").addEventListener("submit", function(event) {
        event.preventDefault();

        var form = event.target;
        var formData = new FormData(form);

        fetch("/comments", {
            method: "POST",
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    window.location.href = "/orders";
                } else {
                    console.error("Error: " + response.status);
                }
            })
            .catch(error => {
                console.error("Error: " + error);
            });
    });
</script>
</body>
</html>