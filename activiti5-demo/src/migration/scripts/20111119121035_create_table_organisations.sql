--// create table organisations
-- Migration SQL that makes the change goes here.
create table organisations(
    id int(11) NOT NULL auto_increment,
    
    full_legal_name varchar(255),
    trading_name varchar(255),
    country_of_operation varchar(255),
    registration_date date,
    operational_address_id int(11),
    business_address_id int(11),
    legal_address_id int(11),
    bic varchar(255),
    other_identification_id int(11),
    representative_officer_id int(11),
    treasury_manager_id int(11),
    main_mandate_holder_id int(11),
    sender_id int(11),
    
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table organisations;

