DROP TABLE  test;
CREATE TABLE test (field int not null);
INSERT INTO test (field) VALUES (?);
SELECT * FROM test
TRUNCATE TABLE test