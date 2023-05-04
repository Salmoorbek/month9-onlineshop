CREATE TABLE orders (
                        id SERIAL PRIMARY KEY,
                        user_id INT NOT NULL REFERENCES usr(id),
                        product_id INT NOT NULL REFERENCES products(id),
                        quantity INT NOT NULL,
                        total_price DECIMAL(10,2) NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT NOW()
);
ALTER TABLE orders ALTER COLUMN id TYPE BIGINT;
ALTER TABLE orders ALTER COLUMN product_id TYPE BIGINT;
ALTER TABLE orders ALTER COLUMN user_id TYPE BIGINT;
ALTER TABLE orders ALTER COLUMN quantity TYPE BIGINT;