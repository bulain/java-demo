--// insert into references 4 catagory
-- Migration SQL that makes the change goes here.
INSERT INTO references VALUES (301,'Catagory','Default','Default','en','','createdBy',SYSDATE,'createdBy',SYSDATE);
INSERT INTO references VALUES (302,'Catagory','Default','д╛хо','zh','','createdBy',SYSDATE,'createdBy',SYSDATE);



--//@UNDO
-- SQL to undo the change goes here.
DELETE FROM references WHERE ID = 301;
DELETE FROM references WHERE ID = 302;

