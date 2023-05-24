INSERT INTO usr (name, user_name, email, password, role, enabled)
VALUES
    ('Salmoor', 'salmor1', 'salmoor@example.com', '$2a$10$cxWIKm8ILPuE8IEpw2jsJ.FjiiSW8T2Qy6HdYE0T/yHzBE/kYRygq','user',true),
    ('Salmoor2', 'salmor2', 'ss@ss.s', '$2a$10$cxWIKm8ILPuE8IEpw2jsJ.FjiiSW8T2Qy6HdYE0T/yHzBE/kYRygq','user',true),
    ('Salmoor3', 'salmor3', 'salmoor3h@example.com', '$2a$10$cxWIKm8ILPuE8IEpw2jsJ.FjiiSW8T2Qy6HdYE0T/yHzBE/kYRygq',  'user',true);

INSERT INTO categories (title)
VALUES  ('Electric'),
        ('Diesel'),
        ('Gas'),
        ('H2O');

INSERT INTO products (name, image_url, description, category_id, price, quantity)
VALUES
    ('Bugatti'    , '/photo/bugatti.jpg'            , 'chiron'              , 1, 1000000, 10),
    ('Mercedes'   , '/photo/mercedes.jpg'           , 'amg'                 , 2, 254001 ,  50),
    ('Lamborghini', '/photo/lamborghini.jpg'        , 'hurricane'           , 3, 235400 ,  25),
    ('Ferrari'    , '/photo/ferrari.jpg'            , 'f49'                 , 4, 878400 ,  20),
    ('Tesla'      , '/photo/tesla.jpg'              , 'model X'             , 1, 102000 ,  1500),
    ('Kia'        , '/photo/kia.jpg'                , 'K5'                  , 1, 10000  ,  9000),
    ('Hyundai'    , '/photo/sonata.jpg'             , 'sonata'              , 2, 14000  ,  500),
    ('Toyota'     , '/photo/camry70.jpg'            , 'camry70'             , 3, 235400 ,  25),
    ('Toyota'     , '/photo/camry50.jpg'            , 'solaris'             , 4, 878400 ,  20),
    ('Toyota'     , '/photo/landcruiser200.jpg'     , 'land cruiser 200'    , 1, 102000 ,  1500),
    ('Acura'      , '/photo/acura.jpg'              , 'tsx'                 , 1, 1000000, 10),
    ('Lexus'      , '/photo/lx600.jpg'              , 'lx600'               , 2, 254001 ,  50),
    ('Lexus'      , '/photo/ls500h.jpg'             , 'ls500h'              , 3, 235400 ,  25),
    ('Lexus'      , '/photo/RX-500hFSPORT.jpg'      , 'RX-500hF-SPORT'      , 4, 878400 ,  20),
    ('Mercedes'   , '/photo/mclaren.jpg'            , 'MacLaren'            , 1, 102000 ,  1500);

INSERT INTO carts (user_id)
VALUES (1), (2);


INSERT INTO cart_items (cart_id, product_id, quantity, price)
VALUES (1, 1, 2, 50000.00),
       (1, 2, 1, 35000.00),
       (2, 4, 1, 25000.00);

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