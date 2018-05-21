--// alter orders add workflow columns
-- Migration SQL that makes the change goes here.
alter table orders add (
	wf_id varchar(20),
	wf_status varchar(20)
);



--//@UNDO
-- SQL to undo the change goes here.
alter table orders drop column wf_id;
alter table orders drop column wf_status;

