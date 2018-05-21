--// create table persons
-- Migration SQL that makes the change goes here.
create table persons(
    id int(11) NOT NULL auto_increment,
 
    name varchar(255),
    role varchar(255),
    client_identification varchar(255),
    trading_party_capacity varchar(255),
    birth_date date,
    birth_name varchar(255),
    given_name varchar(255),
    middle_name varchar(255),
    gender varchar(255),
    language varchar(255),
    nationality varchar(255),
    name_prefix varchar(255),
    name_suffix varchar(255),
    party_id int(11),
    domicile_country varchar(255),
    country_of_birth varchar(255),
    minor_indicator varchar(255),
    residential_status varchar(255),
    city_of_birth varchar(255),
    profession varchar(255),
    province_of_birth varchar(255),
    salary_range varchar(255),
    source_of_wealth varchar(255),
 
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table persons;

