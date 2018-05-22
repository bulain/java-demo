-- // create table blog
-- Migration SQL that makes the change goes here.
CREATE TABLE BLOG(
    ID                 NUMERIC(20)  NOT NULL,
    
    TITLE              VARCHAR2(50) NOT NULL,
    DESCR              VARCHAR2(255) NOT NULL,
    ACTIVE_FLAG        VARCHAR2(1) NOT NULL,
    CREATED_VIA        VARCHAR2(50),
    
    REMARKS            VARCHAR2(500),
    CREATED_AT         DATE,
    CREATED_BY         VARCHAR2(50),
    UPDATED_AT         DATE,
    UPDATED_BY         VARCHAR2(50),
    
    PRIMARY KEY (ID)
);
CREATE SEQUENCE S_BLOG
START WITH 1
INCREMENT BY 1
NOCACHE
NOCYCLE;

-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE BLOG;
DROP SEQUENCE S_BLOG;
