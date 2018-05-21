--// create table profiles
-- Migration SQL that makes the change goes here.
create table profiles(
	id NUMERIC(20,0) NOT NULL,
	person_id NUMERIC(20,0),
	language varchar(50),
	country varchar(50),

	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table profiles;

