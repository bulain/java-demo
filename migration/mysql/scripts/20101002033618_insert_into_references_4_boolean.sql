--// insert into `references` 4 boolean
-- Migration SQL that makes the change goes here.
INSERT INTO `references` VALUES (111,'Reference','Boolean','Boolean','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (112,'Reference','Boolean','²¼¶û','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


INSERT INTO `references` VALUES (801,'Boolean','Yes','Yes','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (802,'Boolean','Yes','ÊÇ','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (803,'Boolean','No','No','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO `references` VALUES (804,'Boolean','No','·ñ','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
DELETE FROM `references` WHERE ID >= 111 AND ID <=112;
delete from `references` where id >=801 and id <=804;

