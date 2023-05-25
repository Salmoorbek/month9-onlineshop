<!DOCTYPE html>
<html>
<head>
    <title>Order Items</title>
    <!-- Ваши стили CSS и другие зависимости -->
</head>
<body>
<h1>Order Items</h1>

<h2>Order ID: ${order.id}</h2>
<h3>User Email: ${userEmail}</h3>

<table>
    <tr>
        <th>Product Name</th>
        <th>Quantity</th>
        <th>Price</th>
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