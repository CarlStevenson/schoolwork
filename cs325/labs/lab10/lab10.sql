--Carl Stevenson
-- I did not give myself enough time to finish this one, I apologize
--1.
select count(*), length from sakila_film order by length desc;
--2.
--3.
--4.
create function addtax(tax decimal)
	returns decimal(7,2)
	deterministic
	no sql
	return tax * 1.06;
--5.
create function isdiv(divdend int, divsor int)
	returns int
	deterministic
	no sql
	declare r int;
	if divdend % divsor >0 then set r 1;
	else set r 0;
	return r;
--6.
create function usdate(ts timestamp)
	returns varchar(30)
	deterministic
	contains sql
	return date_format(ts, '%m-%d-%Y %l:%i%p');

--7.
create function datedue(ts timestamp, days int)
	returns timestamp
	deterministic
	contains sql
	return timestampadd(day, days, ts);

--8.
create function commonfilms(id1 int, id2 int)
	returns int
	reads sql data
	return select count() from sakila_film_actor join sakila_film using(film_id) where id1 is in (select actor_id from sakila_film_actor join sakila_film using(actor_id)) and id2 is in (select actor_id from sakila_film_actor join sakila_film using(actor_id));

