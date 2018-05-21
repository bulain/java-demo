--// create table order_masters
-- Migration SQL that makes the change goes here.
create table order_masters(
    id int(11) NOT NULL auto_increment,
    name varchar(20),
    note varchar(200),
    
    eff_date datetime,
    
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    
    PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table order_masters;

