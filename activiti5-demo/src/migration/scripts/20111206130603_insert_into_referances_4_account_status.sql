--// insert into referances 4 account status
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (121,'Referance','AccountStatus','AccountStatus','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (122,'Referance','AccountStatus','ÕË»§×´Ì¬','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (1301,'AccountStatus','DELE','Deleted','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1302,'AccountStatus','DELE','Deleted','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1303,'AccountStatus','DISA','Disabled','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1304,'AccountStatus','DISA','Disabled','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1305,'AccountStatus','ENAB','Enabled','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1306,'AccountStatus','ENAB','Enabled','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1307,'AccountStatus','FORM','ProForma','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1308,'AccountStatus','FORM','ProForma','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=121 and id <=122;
delete from referances where id >=1301 and id <=1308;

