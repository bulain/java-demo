--// insert into logins
-- Migration SQL that makes the change goes here.
INSERT INTO logins VALUES (101,101,'admin','admin@g.com','admin','Yes','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO logins VALUES (102,102,'noauthorize','noauthorize@g.com','noauthorize','Yes','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO group_logins VALUES (101,101,101,'createdBy',SYSDATE(),'createdBy',SYSDATE());




--//@UNDO
-- SQL to undo the change goes here.
delete from group_logins where id = 101;
delete from logins where id >= 101 and id <= 102;

