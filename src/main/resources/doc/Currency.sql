CREATE TABLE Currency (

id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, 
code VARCHAR(20) NOT NULL, 
chinese_name VARCHAR(80) NOT NULL, 
creator VARCHAR(50), 
create_time TIMESTAMP,
update_time TIMESTAMP,
is_suspend CHAR(1),
UNIQUE (code)

);


