--// insert into referance 4 group type
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (115,'Referance','GroupType','GroupType','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (116,'Referance','GroupType','×éÀàĞÍ','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (1001,'GroupType','ACCOUNT','Account','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1002,'GroupType','ACCOUNT','Account','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1003,'GroupType','INSTITUTION','Institution','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1004,'GroupType','INSTITUTION','Institution','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1005,'GroupType','ORGANISATION','Organisation','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1006,'GroupType','ORGANISATION','Organisation','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1007,'GroupType','INDIVIDUAL','Individual','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1008,'GroupType','INDIVIDUAL','Individual','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=115 and id <=116;
delete from referances where id >=1001 and id <=1008;

