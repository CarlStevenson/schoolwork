-- Carl Stevenson
-- Unfortunately, I ran out of time doing this one, I apologize!
--1. Thank you Mr. Wagner for this one in lab!
select first_name, last_name, sum(amount) as total_spent from sakila_customer join sakila_payment using(customer_id) where store_id = 2 group by customer_id order by total_spent desc limit 10;
--2.
select title, min(rental_date) as firstRented, max(rental_date) as lastRented from sakila_film join sakila_inventory using(film_id) join sakila_rental using(inventory_id) group by film_id having return_date is null;
--3.
select name, count(rental_id) as numInCategory, sum(amount) as total_spent from sakila_category join sakila_film_category using(category_id) join sakila_film using(film_id) join sakila_inventory using(film_id) join sakila_rental using(inventory_id) join sakila_payment using(rental_id) group by category_id;
--4.
select last_name, first_name, if(sum(amount) is null, 0.00,sum(amount))  as total_spent, if(title is null, 'No Rental',group_concat(title order by title separator '; ')) as films_rented from sakila_customer left join sakila_payment using(customer_id) left join sakila_rental using(rental_id) left join sakila_inventory using(inventory_id) left join sakila_film using(film_id) group by sakila_customer.customer_id;
--5.
select first_name, last_name, title, count(customer_id) as rented from sakila_customer join sakila_rental using(customer_id) join sakila_inventory using(inventory_id) join sakila_film using(film_id) group by customer_id, title having count(customer_id)>2 order by last_name; 
--6.
select title from sakila_film where replacement_cost = (select max(replacement_cost) from sakila_film);
--7. Generously given by Mr. Wagner in class!
select * from sakila_customer join sakila_rental using(customer_id) join sakila_inventory using(inventory_id) join sakila_film using(film_id) where (film_id, rental_date) in (select film_id, MAX(rental_date) from sakila_rental join sakila_inventory using(inventory_id) GROUP BY film_id);