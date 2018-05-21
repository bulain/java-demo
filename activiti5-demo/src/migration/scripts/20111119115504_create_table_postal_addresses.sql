--// create table postal_addresses
-- Migration SQL that makes the change goes here.
create table postal_addresses(
    id int(11) NOT NULL auto_increment,
    
    address_type varchar(4),
    department varchar(70),
    sub_department varchar(70),
    street_name varchar(70),
    building_number varchar(16),
    post_code varchar(16),
    town_name varchar(35),
    state varchar(35),
    country varchar(2),
    address_line1 varchar(70),
    address_line2 varchar(70),
    address_line3 varchar(70),
    address_line4 varchar(70),
    address_line5 varchar(70),
    address_line6 varchar(70),
    address_line7 varchar(70),
    
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table postal_addresses;

