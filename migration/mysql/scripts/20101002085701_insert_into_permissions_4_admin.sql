--// insert into permissions 4 admin
-- Migration SQL that makes the change goes here.
INSERT INTO permissions VALUES (101,'Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());



--//@UNDO
-- SQL to undo the change goes here.
delete from permissions where id = 101;

