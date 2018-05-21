--// create table persons
-- Migration SQL that makes the change goes here.
create table persons(
	id NUMERIC(20,0) NOT NULL,
	first_name varchar(50),
	last_name varchar(50),
	
	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table persons;

