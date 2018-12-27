CREATE TABLESPACE tbl_space LOCATION '/home/andrey/db';
CREATE USER user_db;
CREATE DATABASE db WITH OWNER user_db TABLESPACE tbl_space;

ALTER DATABASE db RENAME TO db1;


CREATE SCHEMA main_schema;
CREATE SCHEMA sec_schema;

CREATE TABLE main_schema.normarashod (
  koddet INTEGER,
  kodmat INTEGER,
  nomoper INTEGER,
  norma INTEGER
  CONSTRAINT normarashod_norma_check CHECK (norma >= 0),
  CONSTRAINT normarashod_koddet_pkey PRIMARY KEY (koddet, kodmat, nomoper)

);

CREATE TABLE main_schema.normazatrat (
  koddet INTEGER,
  nomoper INTEGER,
  kodprof INTEGER,
  razr INTEGER,
  kodwork INTEGER,
  time1 INTEGER,
  time2 INTEGER
  CONSTRAINT normazatrat_nomoper_check CHECK (nomoper > 0),
  CONSTRAINT normazatrat_time2_check CHECK (time2 > time1),
  CONSTRAINT normzatrat_koddet_pkey PRIMARY KEY (koddet, nomoper)

);

CREATE TABLE sec_schema.tarif (
  kodwork  INTEGER,
  namework VARCHAR(40) NOT NULL,
  stavka INTEGER,
  CONSTRAINT tarif_stavka_check CHECK (stavka > 0),
  CONSTRAINT tarif_kodwork_pkey PRIMARY KEY (kodwork)
);



ALTER TABLE main_schema.normarashod ADD edizm VARCHAR(40) NOT NULL;
ALTER TABLE main_schema.normarashod ADD CONSTRAINT normarashod_nomoper_check CHECK (nomoper > 0);

ALTER TABLE sec_schema.tarif DROP CONSTRAINT tarif_kodwork_pkey;

ALTER TABLE sec_schema.tarif ADD PRIMARY KEY (namework);

ALTER TABLE sec_schema.tarif DROP CONSTRAINT tarif_stavka_check;
ALTER TABLE sec_schema.tarif ADD CONSTRAINT tarif_stavka_check CHECK (stavka > 200);

ALTER TABLE main_schema.normazatrat DROP kodwork;

ALTER TABLE main_schema.normazatrat ADD tarif_namework VARCHAR(40) REFERENCES sec_schema.tarif (namework);


-- CREATE TABLE sec_schema.normazatrat AS SELECT * FROM main_schema.normazatrat;
ALTER TABLE main_schema.normazatrat SET SCHEMA sec_schema;


INSERT INTO main_schema.normarashod (koddet, kodmat, nomoper, norma, edizm) VALUES (1, 1, 1, 1, 1);
INSERT INTO sec_schema.tarif (kodwork, namework, stavka) VALUES (1, 1, 201);
INSERT INTO sec_schema.normazatrat (koddet, nomoper, kodprof, razr, time1, time2, tarif_namework) VALUES (1, 1, 1, 1, 1, 2, 1);