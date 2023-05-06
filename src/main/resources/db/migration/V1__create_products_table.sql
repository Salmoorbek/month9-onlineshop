CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          image_url VARCHAR(255),
                          description TEXT,
                          category TEXT,
                          price DECIMAL(10,2) NOT NULL,
                          quantity INTEGER NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
ALTER TABLE products ALTER COLUMN id TYPE BIGINT;
ALTER TABLE products ALTER COLUMN price TYPE float8;
ALTER TABLE products ALTER COLUMN quantity TYPE int4;