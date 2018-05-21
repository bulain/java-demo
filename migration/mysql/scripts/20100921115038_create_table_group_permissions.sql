--// create table group_permissions
-- Migration SQL that makes the change goes here.
create table group_permissions(
	id BIGINT(20) NOT NULL auto_increment,
	group_id BIGINT(20),
	permission varchar(50),

	created_by varchar(50),
	created_at datetime,
	updated_by varchar(50),
	updated_at datetime,
	PRIMARY KEY  (id),
	FOREIGN KEY (group_id) REFERENCES groups(id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table group_permissions;

