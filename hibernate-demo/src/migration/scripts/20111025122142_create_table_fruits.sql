--// create table fruits
-- Migration SQL that makes the change goes here.
create table fruits (
    id bigint not null auto_increment, 
    name varchar(20), 
    primary key (id)
)ENGINE=InnoDB default charset=utf8;
create table apples (
    id bigint not null, 
    apple_info varchar(20), 
    primary key (id),
    FOREIGN KEY (id) REFERENCES fruits(id)
)ENGINE=InnoDB default charset=utf8;
create table pears (
    id bigint not null, 
    pear_info varchar(20), 
    primary key (id),
    FOREIGN KEY (id) REFERENCES fruits(id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table pears;
drop table apples;
drop table fruits;
