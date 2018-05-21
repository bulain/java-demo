--// create orders
-- Migration SQL that makes the change goes here.
create table orders(
	id NUMERIC(20,0) NOT NULL,
	name varchar(50),
	note varchar(200),
	
	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table orders;

