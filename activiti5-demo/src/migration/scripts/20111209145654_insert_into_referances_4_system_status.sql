--// insert into referances 4 system status
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (139,'Referance','SystemStatus','SystemStatus','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (140,'Referance','SystemStatus','ϵͳ״̬','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (2301,'SystemStatus','ACTV','Active','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2302,'SystemStatus','ACTV','Active','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2303,'SystemStatus','CLSD','Closed','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2304,'SystemStatus','CLSD','Closed','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2305,'SystemStatus','CLSG','Closing','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2306,'SystemStatus','CLSG','Closing','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2307,'SystemStatus','SUSP','Suspended','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2308,'SystemStatus','SUSP','Suspended','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2309,'SystemStatus','SMTN','SystemMaintenance','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (2310,'SystemStatus','SMTN','SystemMaintenance','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());



--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=139 and id <=140;
delete from referances where id >=2301 and id <=2310;


