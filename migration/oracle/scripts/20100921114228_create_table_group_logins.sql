--// create table group_logins
-- Migration SQL that makes the change goes here.
create table group_logins(
	id NUMERIC(20,0) NOT NULL,
	group_id NUMERIC(20,0),
	login_id NUMERIC(20,0),

	created_by varchar(50),
	created_at date,
	updated_by varchar(50),
	updated_at date,
	PRIMARY KEY  (id),
	FOREIGN KEY (group_id) REFERENCES groups(id)
--	FOREIGN KEY (login_id) REFERENCES logins(id)
);

--//@UNDO
-- SQL to undo the change goes here.
drop table group_logins;

