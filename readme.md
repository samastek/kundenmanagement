# Service für ein Kundenmanagement

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
- [Intellij](https://www.jetbrains.com/idea/)
- [PlantUML](https://plantuml.com/class-diagram)

## Einrichtung

Die Endpoints vom Service wurden nach Design By Contract entwickelt.
Die [Kontrakte](src/main/resources/contracts) können in Postman importiert werden, um die API zu testen.
Der Service wurde nicht vollständig umgesetzt. Deshalb sollte man im Code einen Blick darauf werfen, welche Endpoints zum Testen aufgerufen werden
können.

Zur Einrichtung wird maven benötigt, ist dieses nicht installiert,
kann man den [mvnw](mvnw.cmd) wie folgt benutzen: 
```bash
# build 
./mvnw.cmd clean install
# starten
./mvnw.cmd spring-boot:run -Dspring.profiles.active=<profile>

```

Der Service kann sowohl mit H2-DB als auch mit Postgres gestartet werden.
By Default ist hier H2 konfiguriert.
Wird Postgres bevorzugt, kann dies durch die Angabe des Profils konfiguriert werden. 

- [dev für H2](src/main/resources/application-dev.properties)
- [postgres für postgres](src/main/resources/application-dev.properties)
  

Falls der Service mit Postgres gestartet werden soll, kann die DB am schnellsten mit Docker konfiguriert werden:
Das Profil kann

```bash
./mvnw.cmd spring-boot:run -Dspring.profiles.active=<profile>

docker run --name pg -e POSTGRES_PASSWORD="password" -p 5432:5432 -v postgresql:/var/lib/postgresql/data postgres
```

### Testdaten

Zum Vereinfachen des Testens werden bereits beim Hochfahren der Anwendung Daten in die Datenbank geschrieben.

Diese werden aus der Datei [import.sql](src/main/resources/import.sql) importiert.

**Ansprechpartner bei Fragen:**

- Sami Alzein
- Email: sami.alzein@hotmail.com
- Tel.: 01774737348

