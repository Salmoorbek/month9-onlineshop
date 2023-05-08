INSERT INTO usr (name, user_name, email, password)
VALUES
    ('Salmoor', 'salmor1', 'salmoor@example.com', 'password123'),
    ('Salmoor2', 'salmor2', 'salmoor2@example.com', 'qwerty456'),
    ('Salmoor3', 'salmor3', 'salmoor3h@example.com', '123qwe');

INSERT INTO products (name, image_url, description, category, price, quantity)
VALUES
    ('Bugatti'    , '../static/photo/bugatti.jpg'    , 'Diesel car'   ,'Diesel'  , 1000000, 10),
    ('Mercedes'   , '../static/photo/mercedes.jpg'   , 'Diesel car'   ,'Gas'     , 254001,  50),
    ('Lamborghini', '../static/photo/lamborghini.jpg', 'Diesel Diesel','Diesel'  , 235400,  25),
    ('Ferrari'    , '../static/photo/ferrari.jpg'    , 'Diesel car'   ,'Gas'     , 878400,  20),
    ('Tesla'      , '../static/photo/tesla.jpg'      , 'Electric car' ,'Electric', 102000,  1500);

INSERT INTO carts (user_id)
VALUES (1), (2);

INSERT INTO cart_items (cart_id, product_id, quantity, price)
VALUES (1, 1, 2, 50000.00),
       (1, 2, 1, 35000.00),
       (2, 1, 1, 25000.00);

INSERT INTO orders (user_id, status, total)
VALUES (1, 'Pending', 100000.00),
       (2, 'Shipped', 25000.00);

INSERT INTO order_items (order_id, product_id, quantity, price)
VALUES (1, 1, 2, 50000.00),
       (1, 2, 1, 35000.00),
       (2, 1, 1, 25000.00);

INSERT INTO reviews (user_id, product_id, text, rating)
VALUES (1, 1, 'Great car, very reliable.', 5),
       (2, 1, 'Decent car for the price.', 3),
       (2, 2, 'Awesome car, love it!', 5);