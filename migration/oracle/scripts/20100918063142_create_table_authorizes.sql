--// create table authorizes
-- Migration SQL that makes the change goes here.
create table authorizes(
	id NUMERIC(20,0) NOT NULL,
	controller varchar(50),
	action varchar(50),
	permission varchar(50),

	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table authorizes;

