--// insert into referances 4 cash system type
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (131,'Referance','CashSystemType','CashSystemType','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (132,'Referance','CashSystemType','现金系统类型','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (1801,'CashSystemType','ACHS','ACH','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1802,'CashSystemType','ACHS','ACH','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1803,'CashSystemType','CHCL','ChequeClearing','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1804,'CashSystemType','CHCL','ChequeClearing','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1805,'CashSystemType','RTGS','RTGS','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1806,'CashSystemType','RTGS','RTGS','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());



--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=131 and id <=132;
delete from referances where id >=1801 and id <=1806;



