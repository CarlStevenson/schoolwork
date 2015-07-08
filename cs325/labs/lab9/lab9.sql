--lab9.sql
-- Carl Stevenson

--1.
create table employees(
	id int unsigned auto_increment,
	first_name varchar(14),
	last_name varchar(16),
	salary int unsigned NOT NULL,
	created timestamp default now(),
	updated timestamp default now(),
	primary key(id)
);
--2.
create table employeeshifts(
	id int unsigned default NULL,
	shift_begin timestamp,
	shift_end timestamp,

	foreign key(id) references employees(id)
);
--3.
create view showallshifts as select first_name, last_name, shift_begin, shift_end from employeeshifts right join employees using(id);