--// create table group_logins
-- Migration SQL that makes the change goes here.
create table group_logins(
	id int(11) NOT NULL auto_increment,
	group_id int(11),
	login_id int(11),

	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id),
	FOREIGN KEY (group_id) REFERENCES groups(id)
--	FOREIGN KEY (login_id) REFERENCES logins(id)
)ENGINE=InnoDB default charset=utf8;

--//@UNDO
-- SQL to undo the change goes here.
drop table group_logins;

