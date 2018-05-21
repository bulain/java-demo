--// insert into referances 4 name prefix
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (129,'Referance','NamePrefix','NamePrefix','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (130,'Referance','NamePrefix','称呼','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (1701,'NamePrefix','DOCT','Doctor','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1702,'NamePrefix','DOCT','博士','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1703,'NamePrefix','MADM','Madam','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1704,'NamePrefix','MADM','女士','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1705,'NamePrefix','MISS','Miss','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1706,'NamePrefix','MISS','小姐','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1707,'NamePrefix','MIST','Mister','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1708,'NamePrefix','MIST','先生','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());



--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=129 and id <=130;
delete from referances where id >=1701 and id <=1708;



