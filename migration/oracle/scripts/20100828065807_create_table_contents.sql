--// create table contents
-- Migration SQL that makes the change goes here.
create table contents(
	id NUMERIC(20,0) NOT NULL,
	file_name varchar(50) NOT NULL,
	content_type varchar(255) NOT NULL,
	bytes Blob NULL,

	ref_id NUMERIC(20,0),
	ref_name varchar(50),
	catagory varchar(50),
	lang varchar(50),
	
	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table contents;

