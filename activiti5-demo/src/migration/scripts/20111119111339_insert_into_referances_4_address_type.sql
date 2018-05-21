--// insert into referances 4 AddressType
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (113,'Referance','AddressType','AddressType','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (114,'Referance','AddressType','µØÖ·ÀàĞÍ','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (901,'AddressType','ADDR','Postal','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (902,'AddressType','ADDR','Postal','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (903,'AddressType','BIZZ','Business','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (904,'AddressType','BIZZ','Business','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (905,'AddressType','DLVY','DeliveryTo','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (906,'AddressType','DLVY','DeliveryTo','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (907,'AddressType','HOME','Residential','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (908,'AddressType','HOME','Residential','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (909,'AddressType','MLTO','MailTo','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (910,'AddressType','MLTO','MailTo','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (911,'AddressType','PBOX','POBox','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (912,'AddressType','PBOX','POBox','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=113 and id <=114;
delete from referances where id >=901 and id <=912;

