--// create table alltypes
-- Migration SQL that makes the change goes here.
create table alltypes(
    id bigint(11) NOT NULL auto_increment,
    fvarchar varchar(20),
    fdate date,
    fdatetime datetime,
    fint int(11),
    fbitint bigint(22),
    ffload float(12,2),
    fdouble double(20,2),
    ftimezone varchar(20),
    fcurrency varchar(20),
    flocale varchar(20),
    
    
    version int(11),
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY  (id)
)ENGINE=InnoDB default charset=utf8;


--//@UNDO
-- SQL to undo the change goes here.
drop table alltypes;

