--// create table profiles
-- Migration SQL that makes the change goes here.
create table profiles(
	id BIGINT(20) NOT NULL auto_increment,
	person_id BIGINT(20),
	language varchar(50),
	country varchar(50),

	created_by varchar(50),
	created_at datetime,
	updated_by varchar(50),
	updated_at datetime,
	PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table profiles;

