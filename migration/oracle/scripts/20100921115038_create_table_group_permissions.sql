--// create table group_permissions
-- Migration SQL that makes the change goes here.
create table group_permissions(
	id NUMERIC(20,0) NOT NULL,
	group_id NUMERIC(20,0),
	permission varchar(50),

	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id),
	FOREIGN KEY (group_id) REFERENCES groups(id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table group_permissions;

