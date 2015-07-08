-- Carl Stevenson
-- final project questions

-- 1.
create table branches(
	bid int unsigned AUTO_INCREMENT,
	city varchar(24),
	state varchar(2),
	primary key(bid)
);

-- 2.
create table customers(
	cid int unsigned AUTO_INCREMENT,
	first_name varchar(16),
	last_name varchar(16),
	phone_no int(10) unsigned,
	bid int unsigned,
	overdrawn int(1) unsigned,
	last_transaction timestamp DEFAULT now() on update now(),
	primary key(cid),
	foreign key(bid) references branches(bid)
);

-- 3.
create table transactions(
	cid int unsigned,
	time timestamp default now(),
	diff dec(6,2),
	foreign key(cid) references customers(cid)
);

-- 4.
insert into branches(city, state) values ("Wilkes-Barre", "PA")("Kingston", "PA")("Dallas", "PA")

-- 5.
alter table customers add column username varchar(16) default NULL after phone_no;

-- 6.
set @sum = 0;
select time, diff, @sum = @sum + diff from transactions where cid == 1 order by time;

-- 7.
select first_name, last_name, ifnull(0,SUM(diff)) as balance from customers left join transactions using(cid) group by cid; 

-- 8.
select first_name, last_name, ifnull(0,SUM(diff)) as balance from customers left join transactions using(cid) group by cid order by balance limit 10;

-- 9.
select cid, first_name, last_name from customers where 0> any(select diff from transactions where 
-- 10.
delete from customers where cid != any(select cid from transactions); 

-- 11.
create function accountChange(cid int unsigned, start_date timestamp, end_date timestamp)
	returns int
	set @diff = 0;
	select @diff = sum(diff) from transactions where time >= start_date and time <= end_date and transactions.cid = cid group by transactions.cid;
	return @diff;

-- 12.
create trigger customerTransaction after insert on transactions for each row update customers where cid = new.cid set last_transaction = now();

-- 13.

delimiter $$
create procedure checkOverdrawn(out no int unsigned)
	not deterministic
	modifies sql data
	begin
	update customer set overdrawn = 0;
	update customer set overdrawn = 1, no = no +1 where accountChange(cid, 0, now()) < 0;
	end$$
delimiter ;

-- 14.
create view overdrawn(select cid, first_name, last_name, sum(diff) as balance from customers left join transactions using(cid) group by cid);

-- 15.

create view dormantAccounts(select cid, first_name, last_name, last_transaction from customers where datediff(now(), last_transaction) >90);

-- 16.
create view accountChange30Days(select cid, first_name, last_name, accountChange(cid, date_sub(now(), days 30), now()) from customers left join transactions using(cid));