--// insert into referance 4 category type
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (117,'Referance','CategoryType','CategoryType','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (118,'Referance','CategoryType','类别类型','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (1101,'CategoryType','ACCOUNT','Account','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1102,'CategoryType','ACCOUNT','Account','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1103,'CategoryType','INSTITUTION','Institution','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1104,'CategoryType','INSTITUTION','Institution','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1105,'CategoryType','ORGANISATION','Organisation','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1106,'CategoryType','ORGANISATION','Organisation','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1107,'CategoryType','INDIVIDUAL','Individual','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1108,'CategoryType','INDIVIDUAL','Individual','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=117 and id <=118;
delete from referances where id >=1101 and id <=1108;

