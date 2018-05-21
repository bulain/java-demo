--// create table joda_demo
-- Migration SQL that makes the change goes here.
create table joda_demo(
    id int NOT NULL auto_increment,
    
    xdate date,
    xtime time,
    xdatetime datetime,
    xtimestamp timestamp,
    
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    version bigint,
    
    PRIMARY KEY  (id)
);

--insert joda_demo values(1,'2010-04-03','08:00:00','2010-04-03 08:00:00','2010-04-03 08:00:00');
--insert joda_demo values(2,'2010-04-04','08:00:00','2010-04-04 08:00:00','2010-04-04 08:00:00');
--insert joda_demo values(3,'20100405','080000','20100405080000','20100405080000');

--create sequence joda_demo_sequence start with 1;
--create table joda_demo(
--    id number(11),
--    xdate date,
--    xtime date,
--    xdatetime date,
--    xtimestamp timestamp,
--    PRIMARY KEY  (id)
--);


--//@UNDO
-- SQL to undo the change goes here.
drop table joda_demo;

