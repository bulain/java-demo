--// create table groups
-- Migration SQL that makes the change goes here.
create table groups(
	id NUMERIC(20,0) NOT NULL,
	name varchar(50),
	note varchar(255),

	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table groups;

