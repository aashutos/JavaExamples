CREATE SCHEMA IF NOT EXISTS YOTI;

CREATE TABLE HOOVER_REQUESTS (
  REQUEST_TIMESTAMP BIGINT PRIMARY KEY,
  DIMENSION_ROOM VARCHAR NOT NULL,
  POSITION_HOOVER VARCHAR NOT NULL,
  DIRT_PATCHES VARCHAR NOT NULL,
  INSTRUCTIONS VARCHAR NOT NULL
);

CREATE TABLE HOOVER_RESPONSE (
  REQUEST_TIMESTAMP BIGINT PRIMARY KEY,
  POSITION_HOOVER VARCHAR2 NOT NULL,
  PATCHES_CLEANED INT NOT NULL,
  FOREIGN KEY (REQUEST_TIMESTAMP) REFERENCES HOOVER_REQUESTS(REQUEST_TIMESTAMP) ON DELETE CASCADE
);

COMMIT;
