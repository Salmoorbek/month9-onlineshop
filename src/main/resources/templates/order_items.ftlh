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
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
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
                <form id="commentForm" action="/comments/add" method="post">
                    <input type="hidden" name="product_id" value="${orderItem.productId}">
                    <input type="text" name="comments" placeholder="Enter your comment" required>
                    <input type="number" name="rating" placeholder="Enter your rating" min="1" max="5" required>
                    <button type="submit">Add Comment</button>
                </form>
            </td>
        </tr>
    </#list>
</table>
<script>
    function addComment(event) {
        event.preventDefault();
        if (confirm("Вы уверены, что хотите добавить комментарий?")) {
            var commentForm = document.getElementById("commentForm");
            var formData = new FormData(commentForm);

            fetch("/comments/add", {
                method: "POST",
                body: formData
            })
                .then(response => {
                    if (response.ok) {
                        window.location.href = "/orders";
                    } else {
                        throw new Error("Ошибка при добавлении комментария");
                    }
                })
                .catch(error => {
                    console.error(error);
                });
        }
    }

    var commentForm = document.getElementById("commentForm");
    commentForm.addEventListener("submit", addComment);
</script>

</body>
</html>
