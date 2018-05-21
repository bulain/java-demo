--// create table permissions
-- Migration SQL that makes the change goes here.
create table permissions(
	id NUMERIC(20,0) NOT NULL,
	permission varchar(50),

	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table permissions;
