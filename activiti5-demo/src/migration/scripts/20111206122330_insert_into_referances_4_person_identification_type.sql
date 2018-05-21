--// insert into referances 4 Person Identification Type
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (119,'Referance','PersonIdentificationType','PersonIdentificationType','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (120,'Referance','PersonIdentificationType','个人鉴别类型','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (1201,'PersonIdentificationType','AREG','AlienRegistrationNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1202,'PersonIdentificationType','AREG','AlienRegistrationNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1203,'PersonIdentificationType','CPFA','CPFAccountNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1204,'PersonIdentificationType','CPFA','CPFAccountNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1205,'PersonIdentificationType','DRLC','DriversLicenseNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1206,'PersonIdentificationType','DRLC','DriversLicenseNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1207,'PersonIdentificationType','EMID','EmployerIdentificationNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1208,'PersonIdentificationType','EMID','EmployerIdentificationNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1209,'PersonIdentificationType','IDCD','IdentityCardNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1210,'PersonIdentificationType','IDCD','IdentityCardNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1211,'PersonIdentificationType','NRIN','NationalRegistrationIdentificationNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1212,'PersonIdentificationType','NRIN','NationalRegistrationIdentificationNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1213,'PersonIdentificationType','OTHR','Other','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1214,'PersonIdentificationType','OTHR','Other','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1215,'PersonIdentificationType','PASS','PassportNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1216,'PersonIdentificationType','PASS','PassportNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1217,'PersonIdentificationType','SOCS','SocialSecurityNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1218,'PersonIdentificationType','SOCS','SocialSecurityNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1219,'PersonIdentificationType','SRSA','SRSAccountNumber','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1220,'PersonIdentificationType','SRSA','SRSAccountNumber','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=119 and id <=120;
delete from referances where id >=1201 and id <=1220;
