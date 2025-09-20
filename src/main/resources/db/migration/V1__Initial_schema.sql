CREATE TABLE IF NOT EXISTS bluecatch.tb_customer(
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    paternal_name VARCHAR(100) NOT NULL,
    maternal_name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    birthdate DATE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE INDEX idx_customer_names ON bluecatch.tb_customer(first_name, paternal_name, maternal_name);
CREATE INDEX idx_customer_age ON bluecatch.tb_customer(age);
CREATE INDEX idx_customer_birthdate ON bluecatch.tb_customer(birthdate);

