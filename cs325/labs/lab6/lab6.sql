-- Carl Stevenson

-- 1.
update sakila_film IF length > 120 THEN set rental_duration = rental_duration + 2 ELSE set rental_rate = rental_rate + 2;
-- 2.
update sakila_customer set email = upper(email);
-- 3.
update sakila_film join sakila_inventory using(film_id) join sakila_rental using(inventory_id) IF rental_date < '2006-01-01' THEN set rental_rate = rental_rate/2 ELSE set rental_rate = rental_rate * 2;
--4.
update sakila_customer set active = FALSE where customer_id = IN(select customer_id from sakila_rental where rental_date < '2005-08-01');
--5.
update sakila_customer join sakila_rental using(customer_id) join sakila_inventory using(inventory_id) join sakila_film using(film_id) set first_name = lower(first_name), set last_name = lower(last_name) where title = 'WIFE TURN';
--6. 
update sakila_rental join sakila_customer using(customer_id) set return_date = '2014-10-06' where first_name = 'Lawrence' or first_name = 'Miguel' or first_name = 'Bill' or first_name = 'Adrian';
--7.
delete from sakila_customer where active = FALSE;
--8.
delete sakila_customer from sakila_customer join sakila_rental using(customer_id) join sakila_film using(film_id) where title = 'RAGING AIRPLANE';
--9.
delete from sakila_rental where inventory_id = IN(select inventory_id from sakila_inventory where film_id = IN(select film_id from sakila_film where title = 'RAGING AIRPLANE'));