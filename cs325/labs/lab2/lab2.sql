create table lab2_pets(
  id int(5),
  name varchar(18),
  owner varchar(40),
  animal varchar(18),
  breed varchar(18),
  age tinyint
);

create table lab2_people(
  ssn int(9),
  firstname varchar(16),
  lastname varchar(16),
  email varchar(24),
  phoneno int(10),
  streetline varchar(40),
  city varchar(16),
  state varchar(16),
  zip int(5)
);

create table lab2_tvshows(
  id int(5),
  title varchar(24),
  network varchar(12),
  day char(1),
  time int(4)
);