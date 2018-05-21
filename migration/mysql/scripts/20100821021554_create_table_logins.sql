--// create table logins
-- Migration SQL that makes the change goes here.
create table logins(
	id BIGINT(20) NOT NULL auto_increment,
	person_id BIGINT(20),
	login_name varchar(50),
	email varchar(50),
	hashed_password varchar(50),
	enabled varchar(50),
	
	created_by varchar(50),
	created_at datetime,
	updated_by varchar(50),
	updated_at datetime,
	PRIMARY KEY  (id),
	FOREIGN KEY (person_id) REFERENCES persons(id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table logins;

