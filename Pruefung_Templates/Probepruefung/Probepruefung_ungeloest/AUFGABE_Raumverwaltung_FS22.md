# Probeprüfung: Raumverwaltung (Original-MEP FS22)

> Diese 5-teilige Aufgabe ist die **echte Modulendprüfung vom FS22**. Sie kam als optionale
> Repetitionsaufgabe „Raumverwaltung – Teil 1–5" über die Wochen SW10–SW14 verteilt.
> **Jede der 5 Aufgaben gab 18 Punkte** (≈ 20 Min Arbeitsaufwand pro Teil).
>
> **So übst du am besten:**
> 1. Öffne dein Exam-Template (`OOP_MEP_Probe/`) oder ein frisches Projekt.
> 2. Package: `ch.hslu.oop.rv`
> 3. Löse Teil für Teil SELBST (Stoppuhr: ~20 Min/Teil).
> 4. Erst danach mit `../Probepruefung_geloest/` vergleichen.
>
> **Tipp:** In der echten MEP nur Tests/JavaDoc schreiben, wenn explizit verlangt. Hier sind
> Tests Teil der Aufgabe, also schreib sie!

---

## Teil 1 / 5 — Klasse `Raum` (Grundlagen, Immutable, Exception, equals)

a) Erstelle eine **nicht spezialisierbare** Klasse `Raum` mit je einem Attribut für die **Raumnummer**
   (Ganzzahl) und die **maximale Platzanzahl** (Kapazität). Nur Klassen im selben Package
   `ch.hslu.oop.rv` sollen Raumobjekte erzeugen können. Raumnummer und Platzanzahl sollen direkt
   über den Konstruktor **einmalig (immutable)** gesetzt werden. Beachte die Datenkapselung.

b) Teste mit einem Unit-Test, ob der Konstruktor die Attribute korrekt setzt.

c) Prüfe im Konstruktor die Parameter, so dass nur **Raumnummern 100–999** und **Kapazitäten > 2**
   möglich sind. Wirf im Fehlerfall eine sinnvolle Exception mit individueller Meldung.
   Teste mit drei Unit-Tests alle (Grenz-)Fälle.

d) Implementiere den **equals-Contract** auf `Raum`. Zwei Räume sind gleich, wenn die Raumnummer
   identisch ist.

---

## Teil 2 / 5 — Status-Enum + `RaumVerwaltung` (Enum, Collections)

a) Erstelle eine **Enumeration** für die Stati: **frei**, **belegt**, **gesperrt**.

b) Ergänze auf `Raum` ein Attribut für den Raumstatus (Enum aus a). Nur Klassen im selben Package
   sollen das Attribut **setzen**, aber alle sollen es **lesen** können. Neu erstellte Räume sollen
   immer im Status **frei** sein.

c) Implementiere eine Klasse `RaumVerwaltung` (gleiches Package), die eine Datenstruktur verwendet,
   um eine Menge von `Raum`-Objekten zu speichern. Auf Räume soll **schnell per Raumnummer**
   zugegriffen werden können. Die Datenstruktur soll die Einträge **implizit nach Raumnummer
   sortieren**. *(Tipp: `TreeMap<Integer, Raum>`)*

d) Erzeuge für erste Tests im Konstruktor von `RaumVerwaltung` einen Raum mit Nummer **603** und
   Platzanzahl **12**, und lege ihn in der Datenstruktur ab.

e) Ergänze eine Methode, die anhand einer Raumnummer den entsprechenden Raum zurückliefert.
   Überlege, was im Fehlerfall passieren soll (Datenkapselung!). Teste mit zwei Unit-Tests.

---

## Teil 3 / 5 — Reservation & Freigabe (equals-Test, Logik)

a) Teste die `equals()`-Implementation auf `Raum` mit **EqualsVerifier**.

b) Füge `Raum` eine öffentliche Methode hinzu, die als `boolean` zurückliefert, ob ein Raum frei
   ist. Teste mit Unit-Tests.

c) Implementiere auf `RaumVerwaltung` eine Methode, über die für eine bestimmte **Anzahl Personen**
   automatisch ein passender Raum ausgewählt und **reserviert** wird. Rückgabetyp: `Raum`.
   *(Erster Schritt: der erste genügend grosse und freie Raum darf gewählt werden.)*

d) Ergänze eine Methode, mit der ein reservierter Raum wieder **freigegeben** wird. Wähle einen
   geeigneten Rückgabetyp, um den Erfolg der Operation mitzuteilen.

---

## Teil 4 / 5 — Testdaten, Demo, Optimierung (Überladen, toString)

a) Lege im Konstruktor von `RaumVerwaltung` diese Räume an:

   | Raumnummer | 600 | 602 | 603 | 605 | 610 |
   |---|---|---|---|---|---|
   | Kapazität  | 18  | 6   | 12  | 24  | 12  |

b) Erstelle eine Klasse `Demo` mit `main(...)`. Instanziere eine `RaumVerwaltung` und reserviere je
   einen Raum für **11, 6 und 17** Personen. Gib danach eine Liste aller Räume auf der Konsole aus
   und prüfe, ob nun **drei** Räume belegt sind.

c) Optimiere `reserviere(...)`: Für eine Anforderung soll der jeweils **kleinste noch passende**
   freie Raum gewählt werden.

d) Überlade auf `Raum` (und ggf. weiteren Klassen) die nötigen Methoden (`toString()`), damit ein
   Raum mit `System.out.println(raum)` in einem **für Logging geeigneten Format** ausgegeben wird.

e) Überlade auf `RaumVerwaltung` die Freigabe-Methode so, dass ein Raum auch **nur anhand seiner
   Raumnummer** freigegeben werden kann.

---

## Teil 5 / 5 — Events (eigenes Event, Functional-Interface-Listener, Test)

a) Erstelle eine **spezialisierte Event-Klasse**, die als zusätzliche Attribute einen `Raum` und
   eine Anzahl Plätze enthält. *(Erbe von `java.util.EventObject`!)*

b) Erstelle ein **Functional Interface** für den Event-Listener von a) und implementiere alles
   Nötige, so dass `RaumVerwaltung` zu einer **Event-Quelle** wird (add/remove/fire).

c) Versende bei einer **Reservation** und bei einer **Freigabe** einen Event. Registriere in der
   `Demo`-Klasse mit minimalem Aufwand einen Listener (Lambda!), der die gefeuerten Events per
   **SLF4J/LogBack** auf **INFO-Level** ausgibt (auf der Konsole sichtbar).

d) Implementiere einen **JUnit-Testfall**, der automatisch prüft, ob bei einer Reservation ein
   entsprechender Event ausgelöst wird.

---

### Abgedeckte Prüfungsthemen
Immutable · Datenkapselung · package-private · Exceptions/Validierung · equals/hashCode ·
Enum · Collections (TreeMap) · EqualsVerifier · Methoden überladen · toString · Event/Listener ·
Functional Interface · Lambda · Logging (SLF4J) · JUnit + AssertJ.
