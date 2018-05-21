--// create table generic_identificationes
-- Migration SQL that makes the change goes here.
create table generic_identificationes(
    id int(11) NOT NULL auto_increment,
 
    identification varchar(255),
    scheme_name varchar(255),
    issuer varchar(255),
 
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table generic_identificationes;

