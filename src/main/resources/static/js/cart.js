function removeFromCart(cartItemId) {
    if (confirm("Вы уверены, что хотите удалить этот товар из корзины?")) {
        var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch("/carts/carts/items/remove?cartItemId=" + cartItemId, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            }
        })
            .then(response => {
                if (response.ok) {
                    location.reload();
                }
            })
            .catch(error => {
                console.error("Ошибка при удалении товара из корзины:", error);
            });
    }
}
const quantityInputs = document.querySelectorAll('.quantity-input');
quantityInputs.forEach(input => {
    input.addEventListener('change', function() {
        const cartItemId = this.getAttribute('data-cart-item-id');
        const newQuantity = parseInt(this.value);

        updateCartItemQuantity(cartItemId, newQuantity);
    });
});


function updateCartItemQuantity(cartItemId, newQuantity) {
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    fetch('/carts/carts/items/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken
        },
        body: JSON.stringify({
            cartItemId: cartItemId,
            quantity: newQuantity
        })
    })
        .then(response => {
            if (response.ok) {
                location.reload();
            } else {
                console.error('Ошибка при обновлении количества товара в корзине:', response.status);
            }
        })
        .catch(error => {
            console.error('Ошибка при обновлении количества товара в корзине:', error);
        });
}

function createOrder() {
    if (confirm("Вы уверены, что хотите оформить заказ?")) {
        var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
        var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

        fetch("/orders/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                [csrfHeader]: csrfToken
            }
        })
            .then(response => {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error("Ошибка при оформлении заказа");
                }
            })
            .then(data => {
                window.location.href = "/orders/" + data.order.id;
            })
            .catch(error => {
                console.error(error);
            });
    }
}