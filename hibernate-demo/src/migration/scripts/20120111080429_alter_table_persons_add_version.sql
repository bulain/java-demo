--// alter table persons add version
-- Migration SQL that makes the change goes here.
alter table persons add(
    version int(11)
);


--//@UNDO
-- SQL to undo the change goes here.
alter table persons drop column version ;

