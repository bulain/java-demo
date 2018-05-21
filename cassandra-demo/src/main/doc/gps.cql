CREATE KEYSPACE gps
   WITH replication = {'class' : 'SimpleStrategy', 'replication_factor' : 3};

CREATE TABLE gps.vehicle_log (
  vehicle TEXT,
  time TIMESTAMP,
  lnglat TEXT,
  PRIMARY KEY (vehicle, time)
) WITH COMPACT STORAGE;

