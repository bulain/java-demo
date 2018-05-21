--// create table contact_details
-- Migration SQL that makes the change goes here.
create table contact_details(
    id int(11) NOT NULL auto_increment,
  
    name_prefix varchar(255),
    name varchar(255),
    phone_number varchar(255),
    mobile_number varchar(255),
    fax_number varchar(255),
    email_address varchar(255),
    other varchar(255),
  
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY  (id)
);

--//@UNDO
-- SQL to undo the change goes here.
drop table contact_details;

