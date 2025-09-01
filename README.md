# Forsikringsavtaler

##

Kotlin / Spring Boot:
- **integration-api**: REST API for klienter. Endepunkt for opprettelse av avtaler: `POST /api/v1/avtaler/opprett`
- **fagsystem**: mockes via `FagsystemClient`
- **brevtjeneste**: mockes via `BrevtjenesteClient`

---

## Krav

- JDK **21** (Gradle verktøykjede auto-provisjoneres via Foojay-plugin i `settings.gradle.kts`)
- IntelliJ IDEA (anbefalt)
- OS: Windows/macOS/Linux

---

# macOS/Linux
`./gradlew build`
# Windows
`.\gradlew.bat build`


## Tester / Klienten
For å kjøre tester manuelt selv ligger de i `AvtaleSystemTest.kt`

Alle tester kjøres ved push / opprettelse av PR
