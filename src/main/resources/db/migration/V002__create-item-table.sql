CREATE TABLE IF NOT EXISTS tb_item(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    employee_id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (employee_id) REFERENCES tb_employee(id)
);

