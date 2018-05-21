--// insert into referances 4 cash account type
-- Migration SQL that makes the change goes here.
INSERT INTO referances VALUES (127,'Referance','CashAccountType','CashAccountType','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (128,'Referance','CashAccountType','现金账户类型','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());

INSERT INTO referances VALUES (1601,'CashAccountType','CACC','Current','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1602,'CashAccountType','CACC','Current','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1603,'CashAccountType','CASH','CashPayment','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1604,'CashAccountType','CASH','CashPayment','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1605,'CashAccountType','CHAR','Charges','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1606,'CashAccountType','CHAR','Charges','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1607,'CashAccountType','CISH','CashIncome','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1608,'CashAccountType','CISH','CashIncome','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1609,'CashAccountType','COMM','Commission','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1610,'CashAccountType','COMM','Commission','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1611,'CashAccountType','LOAN','Loan','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1612,'CashAccountType','LOAN','Loan','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1613,'CashAccountType','MGLD','MarginalLending','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1614,'CashAccountType','MGLD','MarginalLending','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1615,'CashAccountType','MOMA','MoneyMarket','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1616,'CashAccountType','MOMA','MoneyMarket','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1617,'CashAccountType','NREX','NonResidentExternal','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1618,'CashAccountType','NREX','NonResidentExternal','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1619,'CashAccountType','ODFT','Overdraft','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1620,'CashAccountType','ODFT','Overdraft','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1621,'CashAccountType','ONDP','OverNightDeposit','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1622,'CashAccountType','ONDP','OverNightDeposit','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1623,'CashAccountType','SACC','Settlement','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1624,'CashAccountType','SACC','Settlement','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1625,'CashAccountType','SLRY','Salary','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1626,'CashAccountType','SLRY','Salary','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1627,'CashAccountType','SVGS','Savings','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1628,'CashAccountType','SVGS','Savings','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1629,'CashAccountType','TAXE','Tax','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1630,'CashAccountType','TAXE','Tax','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1631,'CashAccountType','TRAS','CashTrading','en','','createdBy',SYSDATE(),'createdBy',SYSDATE());
INSERT INTO referances VALUES (1632,'CashAccountType','TRAS','CashTrading','zh','','createdBy',SYSDATE(),'createdBy',SYSDATE());


--//@UNDO
-- SQL to undo the change goes here.
delete from referances where id >=127 and id <=128;
delete from referances where id >=1601 and id <=1632;



