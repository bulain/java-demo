--// create table `references`
-- Migration SQL that makes the change goes here.
create table `references`(
	id int(11) NOT NULL auto_increment,
	name varchar(50) NOT NULL,
	code varchar(50) NOT NULL,
	text varchar(255),
	lang varchar(20) NOT NULL,
	catagory varchar(20),
	
	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table `references`;

