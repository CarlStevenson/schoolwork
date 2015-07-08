-- Carl Stevenson
--1.
-- based off the example function in lecture 17
delimeter $$
create function totalOwed(id int)
returns decimal(5,2)
reads SQL data
deterministic
begin
declare total_spent decimal(5,2);
declare total_paid decimal(5,2);
--selecting the price of every film rented as a base
select ifnull(sum(rental_rate), 0) into total_spent from sakila_rental join sakila_inventory using(inventory_id) join sakila_film using(film_id) where customer_id = id;
-- now we need to add for films that were past due
-- if the difference in the return date and the rental date is greater than 
-- the rental duration and less than or equal to the rental duration + 10,
-- then add 2 x the amount of days to the total in late fees
select ifnull(sum(if(datediff(return_date, rental_date)-(rental_duration+10) >0,0,if(datediff(return_date, rental_date)-rental_duration>0,datediff(return_date, rental_date)-rental_duration, 0))),0) * 2 into total_spent from sakila_rental join sakila_inventory using(inventory_id) join sakila_film using(film_id) where customer_id = id;
-- now add the replacement cost for films greater than 10 days past due
select ifnull(sum(if(datediff(return_date, rental_date)-(rental_duration+10) >0, replacement_cost, 0)),0) into total_spent from sakila_rental join sakila_inventory using(inventory_id) join sakila_film using(film_id) where customer_id = id;
-- now we need to calculate the amount already paid off
select ifnull(sum(amount),0) into total_paid from sakila_payment where customer_id = id;
-- return the amount spent - the amount paid
return total_spent - total_paid;
end$$
delimeter ;
--2.
create trigger film_total after insert on sakila_film for each row set @total = @total + NEW.amount;

--3.
create trigger payment_remove after delete on sakila_payment for each row set @pid = old.payment_id, set @cid = old.customer_id, set @sid = old.customer_id, set @rid = old.rental_id, set @amount = old.amount, set @pdate = old.payment_date, set @lup = old.last_update;

create trigger payment_update after update on sakila_payment for each row if(old.amount>new.amount, set @diff = old.amount-new.amount, set @diff = new.amount-old.amount);

--4.
delimeter $$
create procedure rented(out copies int)
	reads SQL data
	not deterministic
	begin
	-- lists all the items that are currently out, because they haven't been returned yet
	select title from sakila_rental join sakila_inventory using(inventory_id) join sakila_film using(film_id) where return_date is null;
	select found_rows() into copies;
	end$$
delimeter ;
--5.
delimeter $$
create procedure newCustomerRental(in firstname varchar(20), in lastname varchar(20), in titled varchar(20), in sid int, out returnval int)
	modifies sql data
	not deterministic
	begin
	insert into sakila_customer(first_name, last_name, store_id) values(firstname, lastname, sid);
	select inventory_id from sakila_inventory join sakila_rental using(inventory_id) join sakila_film using(film_id) where return_date is not null and title = titled group by inventory_id limit 1;
	set copies = found_rows();
	set customer = last_insert_id();
	if copies >0 then
		insert into sakila_rental(rental_date, inventory_id, customer_id) values(now(), select inventory_id from sakila_inventory join sakila_rental using(inventory_id) join sakila_film using(film_id) where return_date is not null and title = titled group by inventory_id limit 1, customer);
		insert into sakila_payment(customer_id, rental_id, amount, payment_date) values(customer, last_insert_id(), select rental_rate from sakila_film where title = titled, now());
	else set returnval = 0;
	end if;
	end$$
delimeter ;