--// create table parties
-- Migration SQL that makes the change goes here.
create table parties(
    id int(11) NOT NULL auto_increment,
    
    name varchar(255),
    postal_address_id int(11),
    birth_date date,
    province_of_birth varchar(255),
    city_of_birth varchar(255),
    country_of_birth varchar(255),
    other_identification_id int(11),
    country_of_residence varchar(255),
    contact_detail_id int(11),
/*    
    expiry_date date,
    issue_date date,
    identification_id int(11),
    issuer varchar(255),
    identification_type varchar(255),
    drivers_license_number varchar(255),
    social_security_number varchar(255),
    alien_registration_number varchar(255),
    passport_number varchar(255),
    taxidentification_number varchar(255),
    other_identification_id int(11),
    identity_card_number varchar(255),
*/
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table parties;

