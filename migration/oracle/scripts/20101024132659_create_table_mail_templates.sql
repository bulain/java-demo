--// create table mail_templates
-- Migration SQL that makes the change goes here.
create table mail_templates(
	id NUMERIC(20,0) NOT NULL,
	name varchar(255) NOT NULL,
	lang varchar(50) NOT NULL,
	subject varchar(255) NOT NULL,
	body Blob NULL,

	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table mail_templates;

