-- Carl Stevenson
--1.
select date_add(now(), interval 4 month);
--2.
select date_add(date_add(date_add(now(), interval 1 year), interval 2 month), interval 5 day);
--3.
select date_format(date_add(date_add(date_add(now(), interval 1 year), interval 2 month), interval 5 day) ,'%W, %M %e, %Y, %r');
--4.
select time_format(addtime('16:30', '12:00'),'%r');
--5.
select datediff('2014-12-8', now());
--6.
select timediff('2014-12-8 14:30:00', now());
--7.
select last_name, first_name, title from sakila_rental join sakila_customer using(customer_id) join sakila_inventory using(inventory_id) join sakila_film using(film_id) where dayname(rental_date) = 'Thursday' order by last_name;
--8. 
update sakila_customer set sakila_customer.active = 0 where sakila_customer.customer_id in(select customer_id, rental_date from sakila_customer join sakila_rental using(customer_id) where date_sub(now(), YEAR 9) >= rental_date group by customer_id order by rental_date desc limit 1);
--9.
select last_name, first_name, title, (datediff(return_date, rental_date) - rental_duration) as days_overdue from sakila_customer join sakila_rental using(customer_id) join sakila_inventory using(inventory_id) join sakila_film using(film_id) where datediff(return_date, rental_date) - rental_duration > 0; 