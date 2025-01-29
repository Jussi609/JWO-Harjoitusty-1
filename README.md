# Pesäpallo Tilastot -sovellus

Sovellus pesäpallon pelaajien ja heidän tilastojensa hallintaan. Toteutettu Vaadin Flow ja Spring Boot -teknologioilla.

## Ominaisuudet

- Pelaajien hallinta (lisäys, muokkaus, poisto)
- Pelaajien tilastojen seuranta
  - Lyödyt juoksut
  - Tuodut juoksut
  - Kärkilyöntipisteet
  - Pelatut pelit
- Joukkueiden hallinta
- Käyttäjien autentikointi (USER ja ADMIN -roolit)
- Tilastojen tarkastelu ja lajittelu
- Responsiivinen käyttöliittymä
- Monikielisyystuki (suomi, englanti)

## Teknologiat

- Java 17
- Spring Boot 3.4.1
- Vaadin Flow 24.6.2
- H2 Database (kehitysympäristössä)
- PostgreSQL (tuotantoympäristössä)
- Spring Security
- Docker & Docker Compose

## Käynnistäminen

### Kehitysympäristö

1. Varmista että sinulla on asennettuna:
   - Java 17 tai uudempi
   - Maven

2. Kloonaa projekti ja siirry projektin juurikansioon

3. Käynnistä sovellus:

```bash
mvn spring-boot:run
```
4. Avaa selain osoitteessa [http://localhost:8080](http://localhost:8080)

### Docker-ympäristö

1. Varmista että sinulla on Docker ja Docker Compose asennettuna

2. Käynnistä sovellus:

```bash
docker-compose up
```

3. Avaa selain osoitteessa [http://localhost:8080](http://localhost:8080)

## Kirjautuminen

Sovellukseen on määritelty kaksi käyttäjäroolia:
- USER: Peruskäyttäjä, joka voi tarkastella tilastoja
- ADMIN: Ylläpitäjä, joka voi lisätä, muokata ja poistaa pelaajia sekä joukkueita

Testitunnukset:
- Käyttäjä: user / password
- Ylläpitäjä: admin / admin

## Sovelluksen rakenne

- `src/main/java/com/example/application/`
  - `data/`: Entiteetit ja repositoryt
  - `security/`: Autentikaation konfiguraatio
  - `service/`: Bisneslogiikka
  - `views/`: Käyttöliittymänäkymät
  - `i18n/`: Lokalisaatio
- `frontend/`: CSS-tyylit
- `docker/`: Docker-konfiguraatiot

## Itsearviointi

Sovellus täyttää harjoitustyön vaatimukset seuraavasti:

1. Data ja entiteetit (4/5 pistettä):
   - Neljä entiteettiä (Player, Statistics, Team, User)
   - 1-to-1 relaatio (Player-Statistics)
   - m-to-m relaatio (Player-Team)
   - GET, POST, PUT ja DELETE operaatiot toteutettu

2. Suodattimet (3/5 pistettä):
   - Pelaajien haku nimen perusteella
   - Tilastojen lajittelu eri kriteerien mukaan
   - Joukkueiden suodatus

3. Tyylit (5/5 pistettä):
   - Globaalit tyylit
   - Komponenttikohtaiset tyylit
   - Lumo-teeman hyödyntäminen
   - Kustomoitu CSS

4. Ulkoasu (5/5 pistettä):
   - SPA-sovellus
   - Header
   - Navigointipalkki
   - Footer
   - Kolme erilaista sisältösivua

5. Autentikointi (5/5 pistettä):
   - Spring Security integraatio
   - Kirjautumissivu
   - Roolipohjaiset käyttöoikeudet
   - Kustomoitu virheviesti

6. Lisätoiminnallisuudet (3/6 pistettä):
   - Salasanojen salaus BCrypt-algoritmilla
   - Lokalisointi (suomi/englanti)
   - Docker-tuki

### Kehityskohteet

- Käyttöliittymän ulkoasua voisi vielä hioa
- Tilastojen visualisointia voisi parantaa (esim. graafit)
- Testikattavuutta voisi laajentaa
- Hakutoimintoja voisi monipuolistaa

## Huomioitavaa

- Kehitysympäristössä käytetään H2-muistitietokantaa
- Tuotantokäyttöä varten suositellaan PostgreSQL-tietokannan käyttöä
- Testitunnukset tulisi vaihtaa tuotantokäytössä
