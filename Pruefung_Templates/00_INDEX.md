# OOP MEP – Template-Cheatsheet (FS26)

> 25 kompilierbare Java-Templates (default package) zum schnellen Nachschlagen & Kopieren in der Prüfung.
> Alle mit `javac` verifiziert. In der MEP: relevantes Template öffnen, Pattern in dein Exam-Projekt kopieren, anpassen.
>
> **Prüfung:** Mi 24./25. Juni 2026, 13:30–15:30, Rotkreuz · open-book · **KI verboten** · 7 Aufgaben · ~1 Min = 1 Punkt.
> **Workflow je Aufgabe:** Aufgabe lösen → `mvn clean package` (BUILD SUCCESS) → `target/oop_exam_26fs-<uid>.zip` auf ILIAS abgeben.
> **Tests/JavaDoc nur schreiben, wenn in den Hinweisen explizit verlangt!**

---

## 🧱 Grundlagen & Klassen

| # | Datei | Thema |
|---|---|---|
| 01 | `_01_KlassenStruktur.java` | Klassen-Grundgerüst: Felder, Konstruktor, Getter, `toString()` |
| 02 | `_02_Immutable.java` | Immutable Klasse: `final` Felder, keine Setter, Factory-Methoden |
| 03 | `_03_EqualsHashCode.java` | `equals()`/`hashCode()`-Contract (zusammen überschreiben!) |
| 04 | `_04_Comparable_Comparator.java` | Natürliche Ordnung (`Comparable`) + spezielle (`Comparator`) |
| 05 | `_05_InterfaceVsAbstract.java` | Interface ("kann…") vs. abstrakte Klasse ("ist ein…") |
| 06 | `_07_Vererbung.java` | `extends`, `super()`, `@Override`, Wiederverwendung |
| 07 | `_15_Polymorphie.java` | Statischer vs. dynamischer Typ, Dynamic Dispatch, Over*riding/loading* |
| 08 | `_06_EnumMuster.java` | Enum mit Feldern/Methoden/Konstruktor |
| 09 | `_16_Generics.java` | `Box<T>`, generische Methode, `<T extends Comparable<T>>`, Wildcards |
| 25 | `_25_Grundlagen_Kontrollfluss.java` | Datentypen, if/switch-Expression, Schleifen, Cast, Boxing |

**Polymorphie (Prüfungsklassiker!):** Bei *überschriebenen* Methoden zählt der **dynamische** Typ (echtes Objekt) → `Tier t = new Hund(); t.gibLaut()` → Wuff. Bei *Overloading* der **statische** Typ. Nicht verwechseln!
**Generics:** Bei max/min/sortieren eigener Typen → `<T extends Comparable<T>>`. PECS: Producer `extends` (lesen), Consumer `super` (schreiben).
**switch:** Statement (`:` + `break`) vs. Expression (`->` + `yield`, liefert Wert). Im Zweifel Expression-Form (kein vergessenes break).

---

## 🛡️ Fehler & Tests

| # | Datei | Thema |
|---|---|---|
| 10 | `_17_Exceptions.java` | checked vs. unchecked, `try/catch/finally`, multi-catch, try-with-resources |
| 11 | `_18_JUnit_AssertJ.java` | JUnit 6 + AssertJ (gehört nach `src/test/java`, braucht JUnit+AssertJ) |

**Exceptions:** CHECKED `extends Exception` (Compiler erzwingt catch/throws) · UNCHECKED `extends RuntimeException`. Eigene Exception immer mit `(message)` UND `(message, cause)`. `finally` läuft IMMER. Validierung im Konstruktor → `IllegalArgumentException`.
**JUnit:** `@Test`, `@BeforeEach`, `assertEquals(erwartet, ist)`, `assertThrows(Typ.class, () -> code)`. AssertJ: `assertThat(x).isEqualTo(...).isInstanceOf(...)`. Exception testen = `assertThrows`.

---

## 📡 Events & IO

| # | Datei | Thema |
|---|---|---|
| 12 | `_10_Observer_Pattern.java` | Observer-Pattern Grundform |
| 12b| `_24_EventListener_AnonymeKlassen.java` | Event/Listener vertieft: `EventObject`, `@FunctionalInterface`-Listener, 3 Registrier-Arten |
| 13 | `_14_IO_Streams.java` | Byte- vs. Zeichenströme, Datei lesen/schreiben, Encoding |

**Event/Listener:** Trio `addListener` / `removeListener` / `private fireEvent`. Event `extends EventObject`, Listener `extends EventListener`. 3 Arten: benannte Klasse, **anonyme innere Klasse**, **Lambda** (wenn `@FunctionalInterface`). Begründung immer: **senkt Kopplung** (Quelle kennt nur das Interface).
**IO:** Byte-Streams (`DataInputStream`/`DataOutputStream`) für Binär, Zeichen-Streams (`BufferedReader`/`FileReader`) für Text. Beim Binärformat: Reihenfolge beim Lesen = Reihenfolge beim Schreiben.

---

## λ Funktional (Woche 13 – hattest du NICHT als Übung!)

| # | Datei | Thema |
|---|---|---|
| 14 | `_19_Lambdas.java` | Lambdas, `@FunctionalInterface`, `java.util.function`, Methodenreferenzen |
| 15 | `_20_Streams.java` | Stream-Pipeline, Intermediate/Terminal, Collectors, `IntStream`, `Optional` |

**Lambdas:** `() -> 42` · `x -> x*2` · `(a,b) -> a+b`. Standard-Interfaces: `Predicate<T>` T→boolean, `Function<T,R>` T→R, `Consumer<T>` T→void, `Supplier<T>` ()→T. Methodenref: `String::toUpperCase`, `System.out::println`, `Klasse::new`.
**Streams:** `quelle.stream()` → *Intermediate* (`filter/map/sorted/distinct`) → *Terminal* (`collect/count/reduce/forEach`). `Collectors.toList()/joining()/groupingBy()`. `IntStream.range(0,n)` ist obergrenze-**exklusiv**. `average()/findFirst()` → `Optional` → mit `orElse(...)` auspacken. **Schreib in der Prüfung dazu, welche Op intermediate/terminal ist!**

---

## 🗂️ Datenstrukturen

| # | Datei | Thema |
|---|---|---|
| 16 | `_08_Collections.java` | `List`/`ArrayList`, `Map`/`HashMap`/`TreeMap`, `Set`, Iterator |
| 17 | `_09_Arrays.java` | Arrays, mehrdimensional, `Arrays`-Utils |

**Collections:** `List` (Reihenfolge, Duplikate) · `Set` (keine Duplikate) · `Map` (Key→Value). `TreeMap` = sortiert nach Key. `for (var e : liste)` zum Iterieren.

---

## 🎨 GUI & Design (Woche 14/15 – hattest du NICHT als Übung!)

| # | Datei | Thema |
|---|---|---|
| 18 | `_21_GUI_MVC.java` | GUI + MVC mit Swing (Model/View/Controller getrennt) |
| 19 | `_22_Logging.java` | SLF4J + Logback (braucht slf4j-api) |
| 20 | `_23_KopplungKohaesion.java` | Kopplung, Kohäsion & die 10 Designprinzipien |

**GUI/MVC:** Model (Daten, kennt keine GUI) + View (Darstellung) + Controller (verbindet). `button.addActionListener(e -> model...)`. Prüfungsziel = GUI-Code **lesen & ändern**: Datenfluss verfolgen (welcher Listener → welche Model-Methode → welches View-Update).
**Logging:** `private static final Logger LOGGER = LoggerFactory.getLogger(X.class);` · Level error/warn/info/debug/trace · parametrisiert `LOGGER.info("Wert {}", x)` (kein `+`!) · Exception: `LOGGER.error("Fehler", e)`.
**Design:** Hohe Kohäsion INNEN (eine Aufgabe pro Klasse), lose Kopplung NACH AUSSEN (über Interface, per Konstruktor injizieren). 10 Prinzipien: DRY, KISS, YAGNI, SoC, SRP, Information Hiding, Composition>Inheritance, Program-to-Interface, Low Coupling, High Cohesion.

---

## 🏗️ Entwurfsmuster (Bonus)

| # | Datei | Thema |
|---|---|---|
| 21 | `_11_Builder_Pattern.java` | Builder (viele optionale Parameter) |
| 22 | `_12_Singleton_Pattern.java` | Singleton (genau eine Instanz) |
| 23 | `_13_Strategy_Factory.java` | Strategy + Factory |

---

## 📝 Probeprüfung (FS22) – im Ordner `Probepruefung/`

Die **Raumverwaltung** (5 Teile) ist die echte Modulendprüfung von FS22 (je 18 Punkte). Ideale Übung!
- `Probepruefung_ungeloest/` → Aufgabenstellung (selbst lösen, am besten im Exam-Template!)
- `Probepruefung_geloest/` → Referenzlösung (erst SELBST versuchen, dann vergleichen!)

---

## ⏱️ Prüfungsstrategie

1. **Zuerst ALLES durchlesen** → wohin geht die Reise?
2. **Hinweise beachten** (Bean-Function etc.) – Tests/JavaDoc nur wenn verlangt.
3. ~1 Minute = 1 Punkt. Nicht an einer Aufgabe festbeissen.
4. Nach JEDER Aufgabe: `mvn clean package` → ZIP abgeben (Zwischenstände werden bewertet, Diff sichtbar!).
5. Gleiches Projekt einfach mehrfach abgeben.
