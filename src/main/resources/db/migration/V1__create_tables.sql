CREATE TABLE usr (
                     id BIGSERIAL PRIMARY KEY,
                     name VARCHAR(255),
                     user_name VARCHAR(255),
                     email VARCHAR(255),
                     password VARCHAR(255)
);

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          image_url VARCHAR(255),
                          description TEXT,
                          category VARCHAR(255),
                          quantity INT,
                          price DECIMAL(10,2)
);

CREATE TABLE carts (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT REFERENCES usr(id) NOT NULL
);

CREATE TABLE cart_items (
                            id BIGSERIAL PRIMARY KEY,
                            cart_id BIGINT REFERENCES carts(id) NOT NULL,
                            product_id BIGINT REFERENCES products(id) NOT NULL,
                            quantity INTEGER NOT NULL,
                            price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT REFERENCES usr(id) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        status VARCHAR(20) NOT NULL,
                        total DECIMAL(10, 2) NOT NULL
);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             order_id BIGINT REFERENCES orders(id) NOT NULL,
                             product_id BIGINT REFERENCES products(id) NOT NULL,
                             quantity INTEGER NOT NULL,
                             price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE reviews (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT REFERENCES usr(id) NOT NULL,
                         product_id BIGINT REFERENCES products(id) NOT NULL,
                         text TEXT NOT NULL,
                         rating INTEGER NOT NULL,
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
