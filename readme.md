# Service für Kundenmanagement

Dies ist ein REST-Service, der für das Kundenmanagement entwickelt wurde.
Der Service ermöglicht das Anlegen von Geschäftspartnern mit verschiedenen Standorten und Mitarbeitern sowie das Hinterlegen von Anschriften und
Telekommunikationsdaten.

## Anforderungen

Es soll ein neuer REST-Service für ein Gehaltsabrechnungsmanagement entworfen werden. Dieser sollte nach den gängigen REST-Guidelines umgesetzt
werden.
Hintergrund ist der Versand von Gehaltsabrechnungen an Mitarbeiter unserer Kunden.
Gehaltsabrechnungen werden aufgrund der, von diesem Management verwalteten, Datenbasis am Ende jedes Monats von unserer Versandabteilung versendet.
Die gewählte Umsetzung muss diese Funktionalität ermöglichen, aber nicht implementieren.

Rahmenbedingungen / Anforderungen
Im Kundenmanagement sollen Geschäftspartner mit verschiedenen Standorten und mehreren Mitarbeitern angelegt werden können. Hierbei ist zu beachten,
dass ein Mitarbeiter an verschiedenen Standorten, aber nicht an mehreren Geschäftspartnern, gleichzeitig als aktiv geführt werden kann. Ein Standort
kann genau nur einem Geschäftspartner zugeordnet sein. Anschriften und Telekommunikationsdaten sollen zu den obigen Entitäten hinterlegt werden
können.
Daten sollen in einem relationalen Datenbankschema persistiert werden.

Zu allen Entitäten soll es die üblichen CRUD-Operationen und zusätzlich folgende Use-Cases geben:

1) Ausgabe aller Standorte eines Geschäftspartners
2) Ausgabe aller Mitarbeiter eines Geschäftspartners
3) Ausgabe aller Mitarbeiter die an einem Standort arbeiten
4) Mitarbeiter wechselt Standorte
5) Mitarbeiter wechselt Geschäftspartner
6) Standort von Geschäftspartner A wird von Geschäftspartner B übernommen

### Technologien

- [OpenAPI](https://www.openapis.org/)
- [Java](https://www.java.com/en/)
- [Spring Boot](https://spring.io/)
- [Hibernate/Spring JPA](https://spring.io/)
- [Maven](https://maven.apache.org/)
- [Lombok](https://projectlombok.org/)
- [Postgres](https://www.postgresql.org/)
- [Docker](https://docs.docker.com/)
- [Postman](https://www.java.com/en/)
- [IntelliJ](https://www.jetbrains.com/idea/)
- [PlantUML](https://plantuml.com/class-diagram)

## Hinweise bei der Einrichtung

Es befinden sich im [resources](src/main/resources) das [ERD](src/main/resources/diagramme/EDR.png) und das [Klassendiagramm](src/main/resources/diagramme/class-diagram.plantuml) erstellt mit PlantUML.

Die Endpoints vom Service wurden nach "Design By Contract" entwickelt.
Die [Kontrakten](src/main/resources/contracts) können in Postman importiert werden, um die API zu testen.
In dem [Verzeichnis](src/main/resources/postman-v2.1) sind bereits Collections zum Testen der API vorhanden.


Das Build-Tool ist Maven, ist dieses nicht installiert,
kann man den [mvnw](mvnw.cmd) wie folgt benutzen (auf windows): 
```bash
# build 
./mvnw.cmd clean install
# starten
./mvnw.cmd spring-boot:run -Dspring.profiles.active=<profile>

```

Interfaces sowie Modelle werden aus den [Kontrakten](src/main/resources/contracts) generiert.
Deshalb muss vor dem Start der Anwendung zur Generierung der Klassen das folgende ausgeführt werden:

```bash
# build 
./mvnw.cmd clean install
```

Sonst kann der Code nicht kompiliert werden!

Der Service wurde nicht vollständig umgesetzt. Deshalb sollte man im Code einen Blick darauf werfen, welche Endpoints zum Testen aufgerufen werden
können.

## Datenbank
Der Service kann sowohl mit H2 als auch Postgres-DB gestartet werden. 
Zur schnellen Einrichtung der Postgres-DB kann man folgendes ausführen.
```bash
docker run --name pg -e POSTGRES_PASSWORD="password" -p 5432:5432 -v postgresql:/var/lib/postgresql/data postgres
```

Wird H2 bevorzugt, kann man die Postgres-Konfigurationen in [application.properties](src/main/resources/application.properties)
auskommentieren. 

## Testdaten 
Zum Vereinfachen des Testens werden bereits beim Hochfahren der Anwendung Daten in die Datenbank geschrieben.
Diese werden aus der Datei [import.sql](src/main/resources/import.sql) importiert.

---------------------------------------

# Beispiele

## 1) Ausgabe aller Standorte eines Geschäftspartners

```curl
curl 'http://localhost:8080/v1/standorte?geschaeftspartnerId=1'
SELECT * from standort where geschaeftspartner_id=1;
```

## 2) Ausgabe aller Mitarbeiter eines Geschäftspartners

```curl
curl 'http://localhost:8080/v1/mitarbeiter?geschaeftspartnerId=1'
sql zum Verifizieren
SELECT * from mitarbeiter join geschaeftsparnter_mitarbeiter gm on mitarbeiter.id = gm.mitarbeiter_id
where gm.geschaeftspartner_id=1;
```
## 3) Ausgabe aller Mitarbeiter die an einem Standort arbeiten

```curl
curl 'http://localhost:8080/v1/mitarbeiter?standortId=1'
sql zum Verifizieren
SELECT * from mitarbeiter join geschaeftsparnter_mitarbeiter gm on mitarbeiter.id = gm.mitarbeiter_id
where gm.geschaeftspartner_id=1;
```
## 4) Mitarbeiter wechselt Standorte

Die Mitarbeiterin Sabine Müller (ID = 101) arbeitet bei unternehmen für an dem Standort mit der Id 1
wir wollen diese dem Standort mit der ID 51 zugewiesen wird. 

Vor der Operation
```sql
select *
from mitarbeiter m join mitarbeiter_standort ms on m.id = ms.mitarbeiter_id
where vorname = 'Sabine';
```
Operation
```curl
curl --request PUT 'http://localhost:8080/v1/mitarbeiter/101/wechsel-standort' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data '{
  "standorteIds": [51],
  "removeOld": true
}'
```
Verifikation
```sql
select *
from mitarbeiter m join mitarbeiter_standort ms on m.id = ms.mitarbeiter_id
where vorname = 'Sabine';
```

## 5) Mitarbeiter wechselt Geschäftspartner
Die Mitarbeiterin Sabine Müller (ID = 101) wechselt zu Amazon ID = 101

Vor der Operation
```sql
select gm.* from mitarbeiter m join geschaeftsparnter_mitarbeiter gm on m.id = gm.mitarbeiter_id
where m.vorname = 'Sabine';
```
Operation
```curl
curl --location --request PUT 'http://localhost:8080/v1/mitarbeiter/101/wechsel-geschaeftspartner/101' \
--header 'Accept: application/json'
```
Verifikation
```sql
select gm.* from mitarbeiter m join geschaeftsparnter_mitarbeiter gm on m.id = gm.mitarbeiter_id
where m.vorname = 'Sabine';
```

## 6) Standort von Geschäftspartner A wird von Geschäftspartner B übernommen
Standort von unternehmen mit der ID 1 wird von Amazon ID 101 übernommen (mit den Mitarbeitern)

Vor der Operation
```sql
select geschaeftspartner.name, s.name, geschaeftspartner_id, s.id from geschaeftspartner join standort s on geschaeftspartner.id = s.geschaeftspartner_id
where s.name ='Niederlassung Stuttgart';
```
Operation
```curl
curl --location --request PUT 'http://localhost:8080/v1/standorte/1/standort-uebernahme?geschaeftspartnerId=101' \
--header 'Accept: text/plain'
```
Verifikation
```sql
select geschaeftspartner.name, s.name, geschaeftspartner_id, s.id from geschaeftspartner join standort s on geschaeftspartner.id = s.geschaeftspartner_id
where s.name ='Niederlassung Stuttgart';
```


