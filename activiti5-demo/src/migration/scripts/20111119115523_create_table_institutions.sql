--// create table institutions
-- Migration SQL that makes the change goes here.
create table institutions(
    id int(11) NOT NULL auto_increment,
    
    bic varchar(255),
    identification varchar(255),
    scheme_name varchar(255),
    issuer varchar(255),
    name varchar(255),
    postal_address_id int(11),
    clearing_system_member_id int(11),
    
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table institutions;

