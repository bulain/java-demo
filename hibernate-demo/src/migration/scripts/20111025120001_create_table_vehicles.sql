--// create table vehicle
-- Migration SQL that makes the change goes here.
create table vehicles (
    id bigint not null auto_increment, 
    vehicle_type varchar(255) not null, 
    name varchar(20), 
    car_info varchar(20), 
    bike_info varchar(20), 
    primary key (ID)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table vehicles;

