--// insert into referances 4 boolean
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (111,'Referance','Boolean','Boolean','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (112,'Referance','Boolean','²¼¶û','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


INSERT INTO referances VALUES (801,'Boolean','Yes','Yes','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (802,'Boolean','Yes','ÊÇ','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (803,'Boolean','No','No','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (804,'Boolean','No','·ñ','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
DELETE FROM referances WHERE ID >= 111 AND ID <=112;
delete from referances where id >=801 and id <=804;

