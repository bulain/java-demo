--// insert into `references` 4 name
-- Migration SQL that makes the change goes here.
INSERT INTO `references` VALUES (101,'Reference','Reference','Reference','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (102,'Reference','Reference','�ο�����','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (103,'Reference','Language','Language','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (104,'Reference','Language','����','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (105,'Reference','Catagory','Catagory','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (106,'Reference','Catagory','����','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (107,'Reference','Country','Country','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (108,'Reference','Country','����','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (109,'Reference','TimeZone','TimeZone','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (110,'Reference','TimeZone','ʱ��','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
DELETE FROM `references` WHERE ID >= 101 AND ID <=108;