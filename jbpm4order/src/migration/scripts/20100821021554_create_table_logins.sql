--// create table logins
-- Migration SQL that makes the change goes here.
create table logins(
	id int(11) NOT NULL auto_increment,
	person_id int(11),
	login_name varchar(20),
	email varchar(50),
	hashed_password varchar(50),
	enabled varchar(20),
	
	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id),
	FOREIGN KEY (person_id) REFERENCES persons(id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table logins;

