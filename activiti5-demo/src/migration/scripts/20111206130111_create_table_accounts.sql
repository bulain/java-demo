--// create table accounts
-- Migration SQL that makes the change goes here.
create table accounts(
    id int(11) NOT NULL auto_increment,
    
    iban varchar(255),
    scheme_name varchar(255),
    identification varchar(255),
    issuer varchar(255),
    
    status varchar(255),
    name varchar(255),
    currency varchar(255),
    type varchar(255),
    monthly_payment_value decimal(18,5),
    monthly_received_value decimal(18,5),
    monthly_transaction_number int(5),
    average_balance decimal(18,5),
    floor_notification_amount decimal(18,5),
    ceiling_notification_amount decimal(18,5),
    statement_cycle varchar(255),
    closing_date date,
    purpose varchar(255),
    restriction varchar(255),
    
    target_go_live_date date,
    target_closing_date date,
    urgency_flag varchar(255),
    
    created_by varchar(20),
    created_at datetime,
    updated_by varchar(20),
    updated_at datetime,
    PRIMARY KEY  (id)
);


--//@UNDO
-- SQL to undo the change goes here.
drop table accounts;

