--// create table persons
-- Migration SQL that makes the change goes here.
create table persons(
	id BIGINT(20) NOT NULL auto_increment,
	first_name varchar(50),
	last_name varchar(50),
	
	created_by varchar(50),
	created_at datetime,
	updated_by varchar(50),
	updated_at datetime,
	PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table persons;

