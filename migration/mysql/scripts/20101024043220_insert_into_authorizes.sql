--// insert into authorizes
-- Migration SQL that makes the change goes here.
INSERT INTO authorizes VALUES (101,'authorize','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (102,'content','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (103,'group','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (104,'login','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (105,'person','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (106,'profile','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (107,'reference','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (108,'order','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO authorizes VALUES (109,'workflow','*','Admin','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
delete from authorizes where id >= 101 and id <= 109;

