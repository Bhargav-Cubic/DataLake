DROP TABLE IF EXISTS datalake_ui.add_pcc;
CREATE TABLE datalake_ui.add_pcc(SEQUENCE_ID BIGINT NOT NULL AUTO_INCREMENT,
 pcc varchar(500) NOT NULL,
 uploaded_by varchar(50) NOT NULL,
 uploaded_ts DATETIME NOT NULL,
 PRIMARY KEY (SEQUENCE_ID));
