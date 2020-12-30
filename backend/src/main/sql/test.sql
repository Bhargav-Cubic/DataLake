CREATE TABLE user_authentication (
  SEQUENCE_ID bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(500) NOT NULL,
  password varchar(50) NOT NULL,
  PRIMARY KEY (SEQUENCE_ID));
  
insert into user_authentication(username,password) values ('admin','admin');
insert into user_authentication(username,password) values ('offshore','offshore');
insert into user_authentication(username,password) values ('sbagal','password');
insert into user_authentication(username,password) values ('vlimaye','password');







CREATE TABLE user_group_access (
  SEQUENCE_ID bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(500) NOT NULL,
  groupname varchar(50) NOT NULL,
  PRIMARY KEY (SEQUENCE_ID));
  
  
  
insert into user_group_access(username,groupname) values ('admin','DATALAKE-GBT-GLOBAL-L2');
insert into user_group_access(username,groupname) values ('admin1','DATALAKE-GBT-GLOBAL-L3');
insert into user_group_access(username,groupname) values ('admin2','GTR-GBT-GLOBAL-L2');
insert into user_group_access(username,groupname) values ('admin3','DTR-GBT-GLOBAL-L2');



insert into user_group_access(username,groupname) values ('offshore','TR-GBT-GLOBAL-L3');


CREATE TABLE group_menu_access (
  SEQUENCE_ID bigint(20) NOT NULL AUTO_INCREMENT,
  groupname varchar(50) NOT NULL,
  menuname varchar(50) NOT NULL,
  PRIMARY KEY (SEQUENCE_ID));
  
insert into group_menu_access(groupname,menuname) values ('DATALAKE-GBT-GLOBAL-L2','HNS');
insert into group_menu_access(groupname,menuname) values ('DATALAKE-GBT-GLOBAL-L2','USERACCESSFORM');
insert into group_menu_access(groupname,menuname) values ('DATALAKE-GBT-GLOBAL-L2','PCC');
insert into group_menu_access(groupname,menuname) values ('DATALAKE-GBT-GLOBAL-L2','DLFILEUPLOAD');
insert into group_menu_access(groupname,menuname) values ('Data_science','HNS');
insert into group_menu_access(groupname,menuname) values ('data_platform','PCC');
insert into group_menu_access(groupname,menuname) values ('data_platform','USERACCESSFORM');
insert into group_menu_access(groupname,menuname) values ('consulting','DLFILEUPLOAD');





insert into user_group_access(username,groupname) values ('ckumar5','Data_science ');
insert into user_group_access(username,groupname) values ('sbagal','Discover');
insert into user_group_access(username,groupname) values ('kmisra','data_platform');
insert into user_group_access(username,groupname) values ('vlimaye','consulting');
insert into user_group_access(username,groupname) values ('admin','DATALAKE-GBT-GLOBAL-L2');

















