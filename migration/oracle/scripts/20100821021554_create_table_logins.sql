--// create table logins
-- Migration SQL that makes the change goes here.
create table logins(
	id NUMERIC(20,0) NOT NULL,
	person_id NUMERIC(20,0),
	login_name varchar(50),
	email varchar(50),
	hashed_password varchar(50),
	enabled varchar(50),
	
	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id),
	FOREIGN KEY (person_id) REFERENCES persons(id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table logins;

