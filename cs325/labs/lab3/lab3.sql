create table lab3_employees(id int unsigned auto_increment, firstname varchar(16), lastname varchar(16), title varchar(20), salary int unsigned, primary key(id));

insert into lab3_employees(firstname, lastname, title, salary) values('John', 'Smith', 'Manager', 40000),('Mike', 'Johnson', 'Stock Person', 20000),('Ron', 'Burgandy','Cashier',15000);

load data local infile '/home/mathcs/courses/cs325/lab3data.txt' into table lab3_employees fields terminated by '\t' lines terminated by '\n' (lastname, firstname, title, salary);

create table lab3_departments(id int unsigned auto_increment, department_name varchar(16), manager varchar(16), primary key(id));

create table lab3_products(id int unsigned auto_increment, brand varchar(20), product_description varchar(50), quantity int unsigned, price decimal(6,2) unsigned, primary key(id));

create table lab3_aisles(number int unsigned, department int unsigned, primary key(number), foreign key(department) references lab3_departments(id));

create table lab3_shelves(number int unsigned, aisle int unsigned, primary key(number), foreign key(aisle) references lab3_aisles(number));

create table lab3_productplacements(product int unsigned, shelf int unsigned, foreign key(product) references lab3_products(id), foreign key(shelf) references lab3_shelves(number));
