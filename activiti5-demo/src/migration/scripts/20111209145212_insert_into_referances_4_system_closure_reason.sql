--// insert into referances 4 system closure reason
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (137,'Referance','SystemClosureReason','SystemClosureReason','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (138,'Referance','SystemClosureReason','系统关闭原因','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (2201,'SystemClosureReason','ADTW','AllowedDowntimeWindow','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2202,'SystemClosureReason','ADTW','AllowedDowntimeWindow','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2203,'SystemClosureReason','BHOL','BankingHoliday','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2204,'SystemClosureReason','BHOL','BankingHoliday','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2205,'SystemClosureReason','NOOP','NoOperation','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2206,'SystemClosureReason','NOOP','NoOperation','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2207,'SystemClosureReason','RCVR','Recovery','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2208,'SystemClosureReason','RCVR','Recovery','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2209,'SystemClosureReason','SMTN','SystemMaintenance','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2210,'SystemClosureReason','SMTN','SystemMaintenance','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());



--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=137 and id <=138;
delete from referances where id >=2201 and id <=2210;


