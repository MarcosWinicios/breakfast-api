ALTER TABLE tb_item 
    DROP FOREIGN KEY tb_item_ibfk_1;
ALTER TABLE tb_item 
    ADD CONSTRAINT tb_item_ibfk_1 
    FOREIGN KEY (employee_id) 
    REFERENCES tb_employee(id) 
    ON DELETE CASCADE 
    ON UPDATE CASCADE;

