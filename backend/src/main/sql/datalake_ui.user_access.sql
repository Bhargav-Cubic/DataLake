drop table datalake_ui.user_access;
CREATE TABLE datalake_ui.user_access (
  sequence_id bigint(20) NOT NULL AUTO_INCREMENT,
  gbt_id varchar(8) NOT NULL,
  first_name varchar(30) NOT NULL,
  last_name varchar(20) NOT NULL,
  email varchar(50) NOT NULL,
  department varchar(20) DEFAULT NULL,
  job_description varchar(500) DEFAULT NULL,
  user_type varchar(20) NOT NULL,
  pii_access varchar(5) NOT NULL,
  development varchar(50) DEFAULT NULL,
  production varchar(50) DEFAULT NULL,
  sandbox varchar(50) DEFAULT NULL,
  reason varchar(500) DEFAULT NULL,
  requested_by varchar(8) NOT NULL,
  uploaded_ts datetime NOT NULL,
  PRIMARY KEY (sequence_id,gbt_id)
)