# 📘 OOP – SW11: Klassenentwurf, Kopplung & Kohäsion, Events

> **Modul:** Objektorientierte Programmierung (OOP) · HSLU
> **Woche:** SW11 – KW18 (28.04.2026)
> **Themen:** Klassenentwurf (Kapitel 08 OFWJ), Kopplung & Kohäsion (P02), Events / Observer-Pattern (O12)
> **Dozent:** Roland Gisler
> **Quellen:** `Kapitel 08 - Klassenentwurf.pdf`, `P02_IP_KopplungKohäsion.pdf`, `O12_IP_Events.pdf`, `U10_EX_Eventhandling.pdf`
> **Übungen:** U10 – Eventhandling
> **Praxis-Code:** [src/sw11/](../../src/sw11)

---

## 🎯 Lernziele

### Aus Kapitel 08 OFWJ – Klassenentwurf
- **Verantwortlichkeiten** sauber auf Klassen verteilen (Single Responsibility)
- **Code-Smells** erkennen: Code-Duplikation, switch-on-type, parallele Listen, GOD-Klassen
- **Refactoring** als Disziplin verstehen (Methode/Klasse extrahieren, umbenennen, inlinen)
- Den **Kontrollfluss** vom **Datenfluss** trennen
- Das Beispiel "World of Zuul" iterativ verbessern (siehe `OFWJ-chapter08-solutions/`)

### Aus P02 – Kopplung & Kohäsion
- **Hohe Kohäsion** innerhalb einer Klasse (eine Klasse = ein Begriff)
- **Geringe Kopplung** zwischen Klassen (so wenig wissen wie nötig)
- Zusammenhang: gute Kohäsion ⇒ meist automatisch geringere Kopplung
- Refactoring-Werkzeuge der IDE bewusst einsetzen

### Aus O12 – Events
- **Beobachtermuster** (Observer Pattern) verstehen und anwenden
- Java-Beans-Konvention für Events kennen
- Event-Klasse, Listener-Interface, Source/Subject korrekt implementieren
- Lambda-Listener via `@FunctionalInterface`

---

## 📖 Wichtigste Begriffe

| Begriff | Definition |
|---|---|
| **Kohäsion** | Wie eng gehören die Elemente einer Klasse logisch zusammen. Hoch = gut. |
| **Kopplung** | Wie stark hängen Klassen voneinander ab. Gering = gut. |
| **Single Responsibility Principle (SRP)** | Eine Klasse hat **einen** Grund, sich zu ändern. |
| **Code Smell** | Hinweis im Code, dass etwas nicht stimmt (Duplikation, lange Methode, switch-on-type, …). |
| **Refactoring** | Verhaltensgleiche Strukturverbesserung des Codes. |
| **Responsibility-Driven Design** | Frage: "Wer ist verantwortlich?" → dort gehört die Methode hin |
| **Observer Pattern** | 1 Subject → n Listener. Subject benachrichtigt Listener bei Zustandsänderung. |
| **Subject / Observable / Source** | Die Stelle, die Events feuert |
| **Observer / Listener** | Empfänger der Benachrichtigung |
| **Event-Objekt** | Datenträger, der mit dem Event mitgeschickt wird (alter/neuer Wert, Quelle, …) |
| **`@FunctionalInterface`** | Marker für Interfaces mit genau einer abstrakten Methode → Lambda-fähig |
| **`EventObject` / `EventListener`** | Java-Standard-Basisklassen für Events bzw. Listener-Marker-Interface |
| **Push vs. Pull** | Push = Source informiert aktiv; Pull = Listener fragt aktiv. Push ist Regel. |

---

## 🧠 Konzepte & Theorie

### 1. Code-Smells – die wichtigsten

| Smell | Symptom | Behandlung |
|---|---|---|
| **Code-Duplikation** | Gleicher Code an mehreren Stellen | Methode extrahieren / Vererbung |
| **Lange Methode** | > ~20 Zeilen, mehrere Verantwortungen | Methode extrahieren |
| **GOD-Klasse** | Klasse weiss alles, kann alles | Klasse extrahieren / Verantwortlichkeiten teilen |
| **Switch-on-Type** | `switch(typ)` über Klassen-Typen | Polymorphie (Strategy-Pattern) |
| **Parallele Listen** | 2 `List<>` werden synchron gepflegt | Datentyp einführen, eine Liste |
| **Magic Numbers** | `if (x > 25)` ohne Erklärung | `static final` Konstante |
| **Feature Envy** | Methode nutzt fast nur Daten einer **anderen** Klasse | Methode dorthin verschieben |
| **Dead Code** | Wird nie aufgerufen | Löschen |
| **Inappropriate Intimacy** | Zwei Klassen kennen jedes Detail voneinander | Schnittstelle dazwischen |

> 🎯 **Faustregel:** *"If it's hard to test, it's badly designed."*

---

### 2. Kohäsion & Kopplung – die Skala

```
Kohäsion:  niedrig  ●—————————————————●  hoch     ← anstreben
Kopplung:  hoch     ●—————————————————●  niedrig  ← anstreben
```

**Hohe Kohäsion-Beispiele:**
- Klasse `Temperatur` → enthält **nur** Temperatur-Daten und temperaturbezogene Methoden
- Klasse `RechnungsFormatierer` → **nur** Formatierung, nicht Berechnung

**Geringe Kopplung-Beispiele:**
- Klasse `TemperaturVerlauf` kennt **nur** das Listener-**Interface** `ITemperaturListener`, nicht die konkreten Empfänger
- Service-Klassen via Interfaces injizieren, nicht via konkrete Implementierung

> ⚠️ **Klausurfrage typisch:** "Wo erkennen Sie hohe Kopplung im folgenden Code?" → schau auf Imports und harte Klassennamen in Methodenparametern.

---

### 3. Refactoring-Schritte (Kapitel 08 OFWJ)

Iteratives Beispiel "Zuul" zeigt:

| Schritt | Refactoring | Effekt |
|---|---|---|
| **`zuul-bad`** | Alles in einer `Game`-Klasse | GOD-Klasse |
| **`zuul-better`** | `Room`, `Game`, `Parser` getrennt | Verantwortlichkeiten |
| **`zuul-with-enums-v1`** | Befehle als Enum statt String | Type-Safety + IDE-Support |
| **`08-22-zuul-with-items`** | `Item` als eigene Klasse | hohe Kohäsion |
| **`08-33-zuul-with-player`** | Spieler-Status in eigener Klasse | SRP |
| **`08-44-zuul-with-doors`** | `Door`-Klasse statt Map<String, Room> | Begriff modelliert |

> 💡 **Kerneinsicht:** Refactoring ist ein **Skill** und kein einmaliger Akt. Jeder Iterationsschritt verbessert *eine* Sache.

---

### 4. Event-Pattern (Observer) – Aufbau

```
┌──────────────────┐  add/remove    ┌─────────────────────┐
│ TemperaturVerlauf│◄───────────────│ ITemperaturListener │
│   (Subject)      │                │      (Interface)    │
│                  │  fire event    │                     │
│  - verlauf[]     │───────────────►│  void               │
│  - listeners[]   │                │  temperaturChanged  │
│                  │                │  (event)            │
│  + add(celsius)  │                └─────────────────────┘
│  + addListener   │                        ▲
│  + removeListener│                        │ implements
│  - fire()        │                ┌───────┴────────────┐
└──────────────────┘                │  Anonyme Klasse    │
                                    │  Lambda            │
                                    │  Stateful Listener │
                                    └────────────────────┘
```

### 5. Java-Beans-Konvention für Events

| Element | Konvention |
|---|---|
| Event-Klasse | erbt von `java.util.EventObject`, Endung `Event`, **immutable** |
| Listener-Interface | erbt von `java.util.EventListener`, Endung `Listener`, **eine** Methode |
| Methodenname | endet auf **"-ed"** (`changed`, `fired`, `clicked`, …) |
| Source | hat `addXxxListener(...)` und `removeXxxListener(...)` |
| Fire-Methode | **`private`** (oder `protected`), Name `fireXxx(...)` |
| Listener-Iteration | über **Snapshot-Kopie** der Liste (concurrent-modification-safe) |

---

## 📐 Code-Skelette

### Event-Klasse (immer immutable!)
```java
public final class XxxChangeEvent extends java.util.EventObject {
    private static final long serialVersionUID = 1L;
    private final T oldValue;
    private final T newValue;

    public XxxChangeEvent(Object source, T oldValue, T newValue) {
        super(source);
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    public T getOldValue() { return oldValue; }
    public T getNewValue() { return newValue; }
}
```

### Listener-Interface
```java
@FunctionalInterface
public interface XxxChangeListener extends java.util.EventListener {
    void xxxChanged(XxxChangeEvent event);
}
```

### Subject (Source)
```java
public class XxxSource {
    private final List<XxxChangeListener> listeners = new ArrayList<>();
    private T value;

    public void addXxxChangeListener(XxxChangeListener l)    { listeners.add(l); }
    public void removeXxxChangeListener(XxxChangeListener l) { listeners.remove(l); }

    public void setValue(T v) {
        if (Objects.equals(this.value, v)) return;        // kein Event bei No-Change
        T old = this.value;
        this.value = v;
        fireXxxChanged(old, v);
    }

    private void fireXxxChanged(T old, T n) {
        var event = new XxxChangeEvent(this, old, n);
        for (var l : new ArrayList<>(listeners)) l.xxxChanged(event); // Snapshot
    }
}
```

---

## ✏️ U10 – Übung: Eventhandling

Das PDF [`U10_EX_Eventhandling.pdf`](U10_EX_Eventhandling.pdf) hat **2 Hauptaufgaben**:

### Aufgabe 1: PropertyChangeListener (Java-Standard)
Aufbauend auf SW04 (Switchable, Motor, Auto, Licht):
- **Motor** als Event-**Quelle** — feuert `PropertyChangeEvent` bei EIN/AUS
- **Fahrzeug** als Event-**Listener** — `implements PropertyChangeListener`
- Verwendet die **Java-Standard-Klassen** `java.beans.PropertyChangeEvent` / `PropertyChangeListener`

### Aufgabe 2: Eigene Events (TemperaturVerlauf)
- **Eigene Event-Klasse** `TemperaturEvent` mit Enum `MIN | MAX` (Variante 1 aus PDF)
- **`TemperaturVerlauf`** als Event-Quelle — feuert nur bei **echtem** neuen Min/Max
- Hauptanwendung mit **Konsoleneingabe** + **Statistik bei `exit`**
- Listener als **anonyme innere Klasse**

### Lösung im Repo

Saubere Sub-Package-Struktur unter [src/sw11/](../../src/sw11):

```
src/sw11/
├── aufgabe1/                          ← Aufgabe 1: PropertyChangeListener
│   ├── Motor.java                     Event-Quelle (feuert PropertyChangeEvent)
│   ├── Fahrzeug.java                  Event-Listener (implements PropertyChangeListener)
│   └── Aufgabe1Demo.java              Demo + Run-Befehle in JavaDoc
├── aufgabe2/                          ← Aufgabe 2: Eigene Events
│   ├── events/
│   │   └── TemperaturEvent.java       DTO/Datenklasse, erbt EventObject, mit Enum MIN/MAX
│   ├── interfaces/
│   │   └── ITemperaturListener.java   Listener-Interface (I-Prefix), @FunctionalInterface
│   ├── TemperaturVerlauf.java         Event-Quelle mit Min/Max-Tracking
│   └── Aufgabe2Demo.java              Konsoleneingabe + anonyme innere Klasse + Statistik
└── KopplungKohaesionDemo.java         ← Bonus: Theorie-Block (Refactoring schlecht→gut)
```

**Trennung Daten / Vertrag / Logik (Aufgabe 2):**

| Sub-Package | Inhalt | Zweck |
|---|---|---|
| `events/` | [`TemperaturEvent`](../../src/sw11/aufgabe2/events/TemperaturEvent.java) | **Daten** (DTO) — was wird verschickt |
| `interfaces/` | [`ITemperaturListener`](../../src/sw11/aufgabe2/interfaces/ITemperaturListener.java) | **Vertrag** — wer darf empfangen |
| `aufgabe2/` (root) | [`TemperaturVerlauf`](../../src/sw11/aufgabe2/TemperaturVerlauf.java), [`Aufgabe2Demo`](../../src/sw11/aufgabe2/Aufgabe2Demo.java) | **Verhalten** — Logik + Anwendung |

> 💡 **Naming**: Interfaces mit `I`-Prefix (z.B. `ITemperaturListener`). Das ist eigentlich C#-Konvention — Java-Standard wäre `TemperaturListener` ohne Prefix. Im Projekt aber konsistent durchgezogen für klare Unterscheidung.

### Demos ausführen
Vom Verzeichnis `src/` aus:
```bash
cd "C:/Users/elias/Documents/HSLU_MODULE/Semester_2/OOP/src"

# alles auf einmal kompilieren:
javac sw04/*.java sw11/aufgabe1/*.java sw11/aufgabe2/events/*.java sw11/aufgabe2/interfaces/*.java sw11/aufgabe2/*.java sw11/KopplungKohaesionDemo.java

# Aufgabe 1:
java sw11.aufgabe1.Aufgabe1Demo

# Aufgabe 2 (interaktiv - 'exit' beendet):
java sw11.aufgabe2.Aufgabe2Demo

# Bonus Theorie:
java sw11.KopplungKohaesionDemo
```

### Erwarteter Output `Aufgabe1Demo`
```
=== SW11 Aufgabe 1: PropertyChangeListener-Pattern ===

--- Motor einschalten ---
[Fahrzeug] Motor-Property 'isOn' geaendert: false -> true (Quelle: Motor)
[Anonymer Listener] isOn: false -> true
[Lambda] Motor ist jetzt AN
RPM: 2000

--- Motor nochmal einschalten (kein Event!) ---

--- Lambda entfernen, dann ausschalten ---
[Fahrzeug] Motor-Property 'isOn' geaendert: true -> false (Quelle: Motor)
[Anonymer Listener] isOn: true -> false
RPM: 0
```

### Erwarteter Output `Aufgabe2Demo` (Eingabe: 20, 22, 18, 25, 21, 15, exit)
```
Temperatur (C): 20
  >>> Neues MINIMUM: 20.00 C
  >>> Neues MAXIMUM: 20.00 C
Temperatur (C): 22
  >>> Neues MAXIMUM: 22.00 C
Temperatur (C): 18
  >>> Neues MINIMUM: 18.00 C
Temperatur (C): 25
  >>> Neues MAXIMUM: 25.00 C
Temperatur (C): 21               (kein Event - im Min/Max-Bereich!)
Temperatur (C): 15
  >>> Neues MINIMUM: 15.00 C
Temperatur (C): exit

=== Statistik ===
Anzahl Punkte:  6
Minimum:        15.00 C
Maximum:        25.00 C
Durchschnitt:   20.17 C
```

---

## ⚠️ Prüfungsrelevante Hinweise

### Klausurfragen, die fast sicher kommen
1. **Was ist hohe Kohäsion / geringe Kopplung?** (mit Beispiel im Code)
2. **Welche Code-Smells erkennst du?** (Duplikation, GOD-Klasse, switch-on-type)
3. **Skizziere ein Observer-Pattern** (Klassen + Beziehungen)
4. **Warum `private` Konstruktor / `private` fire-Methode?** → Datenkapselung
5. **Warum iteriert `fireXxx` über eine Kopie?** → Concurrent-Modification-Schutz wenn Listener sich selbst deregistriert

### Klassische Fehler
- Listener als **konkrete Klasse** statt Interface verlangen → hohe Kopplung
- Event-Klasse mutable machen → Listener kriegen widersprüchliche Werte
- `fireXxx` öffentlich machen → Aufrufer kann fake-Events feuern
- `synchronized` falsch / nicht setzen → bei Multi-Thread Race-Conditions

### Refactoring-Tools (must-know)
| IntelliJ | VS Code | Aktion |
|---|---|---|
| `Ctrl+Alt+M` | `Ctrl+Shift+R` | Methode extrahieren |
| `Shift+F6` | `F2` | Umbenennen |
| `Ctrl+Alt+L` | `Shift+Alt+F` | Code formatieren |
| `Ctrl+B` | `F12` | Gehe zu Definition |
| `Alt+F7` | `Shift+F12` | Find Usages / Referenzen |

---

## 🔗 Verbindung zu vorherigen / folgenden Wochen

- **SW04 (Switchable, Motor, Auto):** Grundlage für Aufgabe 1 — wir erweitern Motor zur Event-Quelle und Fahrzeug zum Listener.
- **SW09 (TemperaturVerlauf):** Konzept aus SW09 wird in Aufgabe 2 zur Event-Quelle erweitert (feuert bei neuem Min/Max).
- **SW10 (Exception Handling):** `null`-Listener wirft `IllegalArgumentException` (siehe Vorwoche).
- **SW12 (IO-Datenströme):** Listener kann seine Werte in eine Datei loggen → kombiniert mit SW12.
- **Templates:** [`_10_Observer_Pattern.java`](../../src/pruefungsvorbereitung/_10_Observer_Pattern.java) – generische Vorlage für Klausur.
