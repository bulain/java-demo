--// create table references
-- Migration SQL that makes the change goes here.
create table references(
	id NUMERIC(20,0) NOT NULL,
	name varchar(50) NOT NULL,
	code varchar(50) NOT NULL,
	text varchar(255),
	lang varchar(50) NOT NULL,
	catagory varchar(50),
	
	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table references;

