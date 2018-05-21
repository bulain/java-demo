--// insert into persons
-- Migration SQL that makes the change goes here.
INSERT INTO persons VALUES (101,'admin','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO persons VALUES (102,'noauthorize','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
delete from persons where id >= 101 and id <= 102;

