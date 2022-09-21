CREATE TABLE Currency (

id INT NOT NULL AUTO_INCREMENT, 
code VARCHAR(20) NOT NULL, 
chinese_name VARCHAR(80) NOT NULL, 
creator VARCHAR(50), 
create_time DATE,
update_time DATE,
is_suspend CHAR(1),
PRIMARY KEY (id),
UNIQUE (code)

);


