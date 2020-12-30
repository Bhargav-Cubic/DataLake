
drop table datalake_ui.environment_access_temp;
CREATE TABLE datalake_ui.environment_access_temp (
  schema_name varchar(50) NOT NULL,
  environment_name varchar(50) NOT NULL,
  );
  
LOAD DATA LOCAL INFILE 'environment_access' INTO TABLE datalake_ui.environment_access_temp FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';

drop table datalake_ui.environment_access;
CREATE TABLE datalake_ui.environment_access (
  sequence_id bigint(20) NOT NULL AUTO_INCREMENT,
  schema_name varchar(50) NOT NULL,
  environment_name varchar(50) NOT NULL,
  PRIMARY KEY (sequence_id)
  );
  
 insert into  datalake_ui.environment_access(schema_name,environment_name) select schema_name,environment_name from datalake_ui.environment_access_temp;
 
 drop table datalake_ui.environment_access_temp;