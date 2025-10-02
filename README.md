# 📓 MinJournal

Ett skolprojekt där användare kan registrera sig, logga in, skapa journalanteckningar och se statistik över sina humör.

---

## 🚀 Kom igång lokalt

### 1. Klona projektet

Backend och frontend ligger i **separata repositories**, så du behöver klona båda:


```bash
# Klona backend
git clone https://github.com/<ditt-användarnamn>/<ditt-backend-repo>.git
cd <ditt-backend-repo>

# Klona frontend (i en annan mapp)
git clone https://github.com/<ditt-användarnamn>/<ditt-frontend-repo>.git
cd ditt-frontend-repo
````

2. Kör backend (Spring Boot)
-Förkrav-
Java 17+

Maven

MongoDB (körs lokalt på mongodb://localhost:27017)

````
cd minJournalBackend
mvn clean install
mvn spring-boot:run
````

Backend startar nu på:
👉 http://localhost:8080

3. Kör frontend (Angular)
-Förkrav-

Node.js
(v18 eller senare)

Angular CLI

````
JournalFrontend
npm install
ng serve
````
Frontend startar nu på:
👉 http://localhost:4200


🔑 Konfiguration

Backend använder application.properties i minJournalBackend/src/main/resources/.

Exempel:

````
spring.application.name=minJournalBackend
spring.data.mongodb.database=journaldb
spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
server.port=8080
````
✅ Funktioner

Registrera och logga in användare

Skapa journalanteckningar

Filtrera anteckningar efter datum

Visa statistik över humör (procentandelar)

Logga ut/in på olika konton

konton

💡 Tips

Starta MongoDB innan du kör backend

Om du vill nollställa databasen: töm journaldb i MongoDB

Kör alltid backend först (8080), sedan frontend (4200)

👨‍💻 Utveckling

Backend: Java, Spring Boot, MongoDB
Frontend: Angular, TypeScript, Signals API