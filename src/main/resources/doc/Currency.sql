CREATE TABLE Currency (

id int NOT NULL AUTO_INCREMENT, 
code VARCHAR(20) NOT NULL, 
chinese_name VARCHAR(80) NOT NULL, 
creator VARCHAR(50), 
create_time DATETIME,
update_time DATETIME,
is_suspend CHAR(1),
PRIMARY KEY (id),
UNIQUE (code)

);


