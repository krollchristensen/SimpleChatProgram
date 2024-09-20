# Chatprogram i Java
 
Opgaven går ud på at udvikle et chatprogram i Java ved hjælp af multithreading og socket-programmering, der muliggør realtidskommunikation mellem flere klienter og en server. Programmet skal håndtere flere samtidige klientforbindelser, hvor beskeder kan sendes som broadcast (til alle) eller unicast (til en specifik klient). En simpel beskedprotokol skal implementeres med klient-ID, tidsstempel, beskedtype (tekst, fil, emoji) og indhold. Udvidede funktioner inkluderer filoverførsel, emoji-support, chatrum og brugerautentificering.

# Kravspecefication

## Funktionelle Krav

### Serverfunktionalitet
- Serveren skal kunne håndtere flere samtidige klientforbindelser ved hjælp af tråde.
- Serveren skal kunne modtage beskeder fra en klient og videresende dem til alle tilsluttede klienter (broadcast).
- Serveren skal kunne modtage beskeder fra en klient og videresende dem til en specifik tilsluttet klient (unicast).

### Klientfunktionalitet
- Klienten skal kunne forbinde til serveren.
- Klienten skal have en passende front-end.
- Klienten skal kunne sende beskeder til serveren og modtage beskeder fra serveren.

### Protokol
- Implementer en beskedprotokol, der indeholder klientens ID, tidsstempel og beskedindhold.
- Beskeder skal struktureres med et separator-tegn (f.eks. `|`) og kan være af typerne tekst, filoverførsel eller emoji.

### Protokolformater
- **Tekstbesked**: `[CLIENT_ID]|[TIMESTAMP]|TEXT|[MESSAGE_CONTENT]`
- **Filoverførsel**: `[CLIENT_ID]|[TIMESTAMP]|FILE_TRANSFER|[FILE_NAME]|[FILE_SIZE]`
- **Emoji-besked**: `[CLIENT_ID]|[TIMESTAMP]|EMOJI|[EMOJI_CODE]`

### Filoverførsel
- Klienten skal kunne anmode om at sende en fil.
- Serveren skal modtage filen og sende den videre til den relevante klient.

### Emoji-support
- Emojis skal kunne afsendes og modtages som tekstkoder og vises korrekt i klientprogrammet.

### Chatrum
- Serveren skal understøtte oprettelse af separate chatrum, hvor brugere kan deltage og forlade rummet.

### Brugerautentificering
- Serveren skal kræve, at brugere logger ind med et brugernavn og en adgangskode, og validere disse oplysninger før forbindelse accepteres.

## Ikke-funktionelle Krav

- **Systemkompatibilitet**: Systemet skal kunne køre på relevante operativsystemer (Windows, macOS, Linux).
- **Brugervenlighed**: Klientens GUI skal være intuitiv og let at navigere.
- **Dokumentation**: Der skal være god dokumentation for både kode og systemets funktioner.
- **Fejlhåndtering**: Systemet skal have robust fejlhåndtering og klare fejlmeddelelser.
