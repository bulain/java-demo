--// create table mail_templates
-- Migration SQL that makes the change goes here.
create table mail_templates(
	id int(11) NOT NULL auto_increment,
	name varchar(255) NOT NULL,
	lang varchar(20) NOT NULL,
	subject varchar(255) NOT NULL,
	body LongBlob NULL,

	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table mail_templates;

