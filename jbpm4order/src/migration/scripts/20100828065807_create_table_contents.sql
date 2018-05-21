--// create table contents
-- Migration SQL that makes the change goes here.
create table contents(
	id int(11) NOT NULL auto_increment,
	file_name varchar(50) NOT NULL,
	content_type varchar(255) NOT NULL,
	bytes LongBlob NULL,

	ref_id int(11),
	ref_name varchar(50),
	catagory varchar(50),
	lang varchar(20),
	
	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table contents;

