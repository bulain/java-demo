--// create table profiles
-- Migration SQL that makes the change goes here.
create table profiles(
	id int(11) NOT NULL auto_increment,
	person_id int(11),
	language varchar(50),
	country varchar(50),

	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table profiles;

