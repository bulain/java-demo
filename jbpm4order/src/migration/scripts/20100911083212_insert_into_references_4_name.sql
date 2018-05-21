--// insert into `references` 4 name
-- Migration SQL that makes the change goes here.
INSERT INTO `references` VALUES (101,'Referance','Referance','Referance','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (102,'Referance','Referance','参考名称','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (103,'Referance','Language','Language','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (104,'Referance','Language','语言','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (105,'Referance','Catagory','Catagory','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (106,'Referance','Catagory','分类','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (107,'Referance','Country','Country','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (108,'Referance','Country','国家','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (109,'Referance','TimeZone','TimeZone','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (110,'Referance','TimeZone','时区','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
DELETE FROM `references` WHERE ID >= 101 AND ID <=108;