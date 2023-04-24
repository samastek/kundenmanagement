---------------------------------------------------------------- DATEV BEGINN ------------------------------------------------------------------------

INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'Datev');

----------------------- Datev hat zwei Standorte, an denen insgesamt 6 Mitarbeiter tätig sind -------------------------------------

------------ Datev Stuttgart STANDORT (1) ------------
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '10', 'Deutschland', 'Stuttgart', '70173', 'Königstraße', null);
INSERT INTO STANDORT (id, name, anschrift_id, geschaeftspartner_id)
VALUES (nextval('standort_seq'), 'Niederlassung Stuttgart', currval('anschrift_seq'), currval('geschaeftspartner_seq'));

-- MITARBEITER 1
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '90', 'Deutschland', 'Nürnberg', '90449', 'Herriedenerstr.', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'sami.alzein@hotmail.com', '1997-09-01', 'Alzein', '01774737348', 'sami', currval('anschrift_seq'));
INSERT INTO MITARBEITER_STANDORT (standortid, mitarbeiterid)
VALUES (currval('standort_seq'), currval('mitarbeiter_seq'));

-- MITARBEITER 2
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '15', 'Deutschland', 'Berlin', '10115', 'Friedrichstraße', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'max.mustermann@gmail.com', '1990-01-01', 'Mustermann', '0176-12345678', 'Max', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));
INSERT INTO MITARBEITER_STANDORT (standortid, mitarbeiterid)
VALUES (currval('standort_seq'), currval('mitarbeiter_seq'));

-- MITARBEITER 3
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '10', 'Deutschland', 'Hamburg', '20354', 'Neuer Jungfernstieg', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'sabine.mueller@yahoo.com', '1985-06-10', 'Müller', '0157-87654321', 'Sabine', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));
INSERT INTO MITARBEITER_STANDORT (standortid, mitarbeiterid)
VALUES (currval('standort_seq'), currval('mitarbeiter_seq'));
------------ Datev Stuttgart STANDORT (1) ENDE ------------


------------ Datev Standort München Beginn (2) ------------
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '20', 'Deutschland', 'München', '80331', 'Marienplatz', null);
INSERT INTO STANDORT (id, name, anschrift_id, geschaeftspartner_id)
VALUES (nextval('standort_seq'), 'Niederlassung München', currval('anschrift_seq'), currval('geschaeftspartner_seq'));
-- Standort Datev Standort München Ende

-- MITARBEITER 4
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '5', 'Deutschland', 'München', '80333', 'Maximilianstraße', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'hans.peter@web.de', '1995-02-28', 'Peter', '0174-98765432', 'Hans', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));
INSERT INTO MITARBEITER_STANDORT (standortid, mitarbeiterid)
VALUES (currval('standort_seq'), currval('mitarbeiter_seq'));

-- MITARBEITER 5
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '25', 'Deutschland', 'Stuttgart', '70178', 'Kleiner Schlossplatz', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'lena.meier@gmail.com', '1998-09-15', 'Meier', '0159-65432109', 'Lena', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));
INSERT INTO MITARBEITER_STANDORT (standortid, mitarbeiterid)
VALUES (currval('standort_seq'), currval('mitarbeiter_seq'));
-- MITARBEITER 6
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '20', 'Deutschland', 'Hannover', '30159', 'Georgstraße', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'lena.meier@gmail.com', '1998-09-15', 'Meier', '0159-65432109', 'Lena', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));
INSERT INTO MITARBEITER_STANDORT (standortid, mitarbeiterid)
VALUES (currval('standort_seq'), currval('mitarbeiter_seq'));

------------ Datev Standort München ENDE (2) ------------


-------------------------------------------------------------------------------- DATEV Ende ----------------------------------------------------------


-------------------------------------------------------------------------------- adorsys BEGINN ------------------------------------------------------

INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'adorsys');

----------------------- adorsys hat einen Standort -------------------------------------


------------ Hamburg STANDORT Beginn ------------
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '15', 'Deutschland', 'Hamburg', '20095', 'Mönckebergstraße', null);
INSERT INTO STANDORT (id, name, anschrift_id, geschaeftspartner_id)
VALUES (nextval('standort_seq'), 'Niederlassung Hamburg', currval('anschrift_seq'), currval('geschaeftspartner_seq'));

------------ MITARBEITER Beginn
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'john.smith@siemens.com', '1985-05-12', 'Smith', '0176554321', 'John', currval('anschrift_seq'));
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'peter.meier@siemens.com', '1988-09-21', 'Meier', '0401234567', 'Peter', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));

------------ MITARBEITER ENDE
------------ Hamburg STANDORT Ende ------------

---------------------------------------------------------------- adorsys ENDE ------------------------------------------------------------------------


---------------------------------------------------------------- Amazon BEGINN -----------------------------------------------------------------------
INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'Amazon');
-- MITARBEITER ANSCHRIFT
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '19', 'Deutschland', 'München', '80686', 'Harrasstraße', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'maria.schmidt@hotmail.com', '1991-12-23', 'Schmidt', '01773658375', 'Maria', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));
-- Anschrift STANDORT
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '8', 'Deutschland', 'Frankfurt', '60313', 'Mainzer Landstraße', null);
INSERT INTO STANDORT (id, name, anschrift_id, geschaeftspartner_id)
VALUES (nextval('standort_seq'), 'Niederlassung Frankfurt', currval('anschrift_seq'), currval('geschaeftspartner_seq'));
INSERT INTO MITARBEITER_STANDORT (standortid, mitarbeiterid)
VALUES (currval('standort_seq'), currval('mitarbeiter_seq'));
---------------------------------------------------------------- Amazon Ende -------------------------------------------------------------------------


-- Standort Beginn
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '35', 'Deutschland', 'Berlin', '10117', 'Unter den Linden', null);
INSERT INTO STANDORT (id, name, anschrift_id, geschaeftspartner_id)
VALUES (nextval('standort_seq'), 'Niederlassung Berlin', currval('anschrift_seq'), currval('geschaeftspartner_seq'));
-- Standort Ende


------------------------------------------------------------------------------------------------------------


-------------- Freie Mitarbeiter BEGINN ----------------
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '50', 'Deutschland', 'Nürnberg', '90480', 'Fürtherstr.', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'Richard.Fuss@hotmail.com', '1967-10-10', 'Fuss', '01774737348', 'Richard', currval('anschrift_seq'));
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '10', 'Deutschland', 'Nürnberg', '90449', 'Bahnhofsstr.', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'max.vogel@hotmail.com', '1997-10-01', 'Vogel', '01774737348', 'Max', currval('anschrift_seq'));
-------------- Freie Mitarbeiter BEGINN ----------------


-- weitere Geschaeftspartner und sonstige Daten
INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'Campuspoint');
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '10', 'Deutschland', 'Nürnberg', '90449', 'Bahnhofsstr.', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'max.vogel@hotmail.com', '1997-10-01', 'Vogel', '01774737348', 'Max', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));



INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'Amazon');
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '5', 'Deutschland', 'Berlin', '10115', 'Alexanderplatz', 'Berlin Mitte');
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'max.mustermann@gmail.com', '1989-06-13', 'Mustermann', '01573476548', 'Max', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));

-- 5
INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'BMW');
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '15', 'Deutschland', 'München', '80331', 'Marienplatz', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'anna.mueller@yahoo.de', '1992-08-22', 'Müller', '01764448371', 'Anna', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));

--- 6

INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'Bosch');
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '32', 'Deutschland', 'Stuttgart', '70469', 'Ludwigstraße', 'Bad Cannstatt');
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'peter.lang@web.de', '1985-01-10', 'Lang', '01748884932', 'Peter', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));


-- 7

INSERT INTO GESCHAEFTSPARTNER (id, name)
VALUES (nextval('geschaeftspartner_seq'), 'Siemens');
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '19', 'Deutschland', 'München', '80686', 'Harrasstraße', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'maria.schmidt@hotmail.com', '1991-12-23', 'Schmidt', '01773658375', 'Maria', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));



INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '10', 'Deutschland', 'Berlin', '10999', 'Skalitzer Straße', null);
INSERT INTO MITARBEITER (id, email, geburtsdatum, nachname, telefonnummer, vorname, anschrift_id)
VALUES (nextval('mitarbeiter_seq'), 'jane.doe@siemens.com', '1990-01-01', 'Doe', '017612345678', 'Jane', currval('anschrift_seq'));
INSERT INTO GESCHAEFTSPARNTER_MITARBEITER (id, active, geschaeftspartner_id, mitarbeiter_id)
VALUES (nextval('gm_seq'), true, currval('geschaeftspartner_seq'), currval('mitarbeiter_seq'));
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '24', 'Deutschland', 'Frankfurt', '60325', 'MesseTurm', null);
INSERT INTO ANSCHRIFT (id, hausnummer, land, ort, plz, strasse, zusatz)
VALUES (nextval('anschrift_seq'), '45', 'Deutschland', 'Hamburg', '20354', 'Colonnaden', '4. Etage');





