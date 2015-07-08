--1.
select title from sakila_film where language_id in(select language_id from sakila_language where name = 'mandarin') order by sakila_film.title;
--2.
select distinct title from sakila_film where film_id in(select film_id from sakila_inventory where store_id = 1) and film_id not in(select film_id from sakila_inventory where store_id = 2);
--3.
select payment_date, amount from sakila_payment where customer_id in(select customer_id from sakila_customer where first_name = 'STACY' and last_name = 'CUNNINGHAM') and staff_id in(select staff_id from sakila_staff where first_name = 'Jon' and last_name = 'Stephens');
--4. 
select distinct first_name, last_name, rental_date from sakila_customer join sakila_rental using(customer_id) join sakila_inventory using(inventory_id) join sakila_film using(film_id) where title = 'WORKING MICROCOSMOS';
--5.
select distinct title from sakila_film join sakila_film_actor using(film_id) join sakila_actor using(actor_id) where first_name = 'BURT' and last_name = 'TEMPLE' order by release_year;
--6.
select last_name, first_name, ifnull(title, 'no rentals') as title from sakila_customer left join sakila_rental using(customer_id) left join sakila_inventory using(inventory_id) join sakila_film using(film_id) order by last_name, first_name, title;
--7.
select title, ifnull(rental_date, 'not rented') as rental_date from sakila_inventory left join sakila_film using(film_id) left join sakila_rental using(inventory_id) order by title, rental_date;
--8.
select last_name, title, name from sakila_rental join sakila_customer using(customer_id) join sakila_inventory using(inventory_id)join sakila_film using(film_id) join sakila_film_category using(film_id) join sakila_category using(category_id) where return_date is NULL and (name = 'Family' or name = 'Children');
