--// insert into groups
-- Migration SQL that makes the change goes here.
INSERT INTO groups VALUES (101,'Admin','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO group_permissions VALUES (101,101,'Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());



--//@UNDO
-- SQL to undo the change goes here.
delete from group_permissions where id = 101;
delete from groups where id = 101;

