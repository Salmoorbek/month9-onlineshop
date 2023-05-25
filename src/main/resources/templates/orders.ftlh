<!DOCTYPE html>
<html>
<head>
    <title>Order</title>
    <meta name="_csrf" content="${_csrf.token}" />
    <meta name="_csrf_header" content="${_csrf.headerName}" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
<div class="navbar">
    <ul>
        <li><a href="/">Home</a></li>
        <li><a href="product">Cars</a></li>
        <li><a href="#">Services</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
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
<h1>My Orders</h1>

<table>
    <tr>
        <th>Order ID</th>
        <th>Created At</th>
        <th>Status</th>
        <th>Total</th>
    </tr>
    <#if userOrders?has_content && userOrders?size gt 0>
        <#list userOrders as order>
            <tr>
                <td>${order.id}</td>
                <td>${order.createdAt}</td>
                <td>${order.status}</td>
                <td>${order.total}</td>
                <td><a href="orders/${order.id}/items">order items</a></td>
            </tr>
        </#list>
    <#else>
        <tr>
            <td colspan="4">No orders found.</td>
        </tr>
    </#if>
</table>
<script src="/js/order.js"></script>
</body>
</html>