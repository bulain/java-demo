--// First migration.
-- Migration SQL that makes the change goes here.
CREATE SEQUENCE SERVICE_SEQUENCE
 START WITH     10000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;


--//@UNDO
-- SQL to undo the change goes here.
DROP SEQUENCE SERVICE_SEQUENCE;

