--// create table groups
-- Migration SQL that makes the change goes here.
create table groups(
    id int(11) NOT NULL auto_increment,
    
    name varchar(20),
    type varchar(20),
    description varchar(255),
    
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table groups;

