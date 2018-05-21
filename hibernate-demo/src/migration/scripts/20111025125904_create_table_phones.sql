--// create table phones
-- Migration SQL that makes the change goes here.
create table tel_phones (
    id bigint not null, name varchar(20), 
    tel_info varchar(20), 
    primary key (id)
)ENGINE=InnoDB default charset=utf8;
create table cell_phones (
    id bigint not null, name varchar(20), 
    cell_info varchar(20), 
    primary key (id)
)ENGINE=InnoDB default charset=utf8;

--//@UNDO
-- SQL to undo the change goes here.
drop table cell_phones;
drop table tel_phones;
