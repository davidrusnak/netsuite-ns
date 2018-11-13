-- DDL of SQL table used in old service implementation
CREATE TABLE Session
(
  "sId"      VARCHAR(255) PRIMARY KEY,
  "sEmail"   VARCHAR(255) NOT NULL,
  "dCreated" TIMESTAMP    NOT NULL
);
