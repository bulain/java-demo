--// create table group_users
-- Migration SQL that makes the change goes here.
create table group_users(
	id bigint(11) NOT NULL auto_increment,
	group_id bigint(11),
	user_id bigint(11),

	created_by varchar(20),
	created_at datetime,
	updated_by varchar(20),
	updated_at datetime,
	PRIMARY KEY  (id),
	FOREIGN KEY (group_id) REFERENCES groups(id),
	FOREIGN KEY (user_id) REFERENCES users(id)
)ENGINE=InnoDB default charset=utf8;

--//@UNDO
-- SQL to undo the change goes here.
drop table group_users;

