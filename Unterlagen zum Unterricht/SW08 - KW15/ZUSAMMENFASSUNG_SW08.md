# OOP – SW08: Online Test 1 – Rückblick & Korrektur

> **Modul:** Objektorientierte Programmierung (OOP) · HSLU
> **Woche:** SW08 – KW15 (Osterpause / Testauswertung)
> **Thema:** Online Test 1 (Inputs O01–O07, P01, E01) – Ergebnisanalyse & Korrekturen
> **Ergebnis:** 48 / 58 Punkte (82.76 %)

---

## 🎯 Zweck dieser Woche

SW08 fiel auf die Osterpause. Der **Online Test 1** wurde am 12. April 2026 geschrieben und deckt den gesamten Stoff der Wochen **SW01–SW07** ab (Inputs O01–O07, P01 und E01). Diese Zusammenfassung analysiert die Fehler im Test und stellt die korrekten Antworten bereit.

---

## ✅ Testergebnis – Übersicht

| Frage | Thema | Punkte | Status |
|---|---|---|---|
| O05 – do-while | Iteration | 0/2 | ❌ |
| P01 – Sichtbarkeit/Scope | Scope-Reihenfolge | 0/2 | ❌ |
| O03 – Ausdrücke (2) | `20L / (d + 2.3f)` | 2/2 | ✅ |
| O03 – Ganzzahl-Datentypen | byte/long | 2/2 | ✅ |
| O03 – Elementare Datentypen | Anzahl = 8 | 2/2 | ✅ |
| O01 – OOP Grundlagen | Attribute/Methoden | 1/2 | ⚠️ |
| O05 – while | Kprim | 2/2 | ✅ |
| P01 – Information Hiding | Kprim | 1/2 | ⚠️ |
| O02 – Klassenrumpf | Reihenfolge | 2/2 | ✅ |
| O04 – switch | `break` Keyword | 2/2 | ✅ |
| O07 – Vererbung Grundlagen | Spezialisierung | 1/2 | ⚠️ |
| O02 – Packages | Vollqualifizierter Name | 2/2 | ✅ |
| O01 – Klassen | Kprim | 2/2 | ✅ |
| O02 – Namensgebung | Konventionen | 2/2 | ✅ |
| O04 – if | Ausgabe bestimmen | 2/2 | ✅ |
| O05 – for | Ausgabe bestimmen | 2/2 | ✅ |
| O03 – Defaultdatentyp | `int` | 1/1 | ✅ |
| O03 – Fliesskomma | float/double Genauigkeit | 1/2 | ⚠️ |
| O03 – Ausdrücke (1) | `6 / i + 1.3f` | 2/2 | ✅ |
| O06 – Schnittstellen | Kprim | 2/2 | ✅ |
| O07 – Vererbung Keyword | `extends` vs `implements` | 0/1 | ❌ |
| O02 – this | Kprim | 2/2 | ✅ |
| O03 – Ausdrücke (3) | `10 / 4 + 3` | 2/2 | ✅ |
| O06 – Abstrakte Klassen | Kprim | 2/2 | ✅ |
| O02 – Attribut-Deklaration | `private long demo` | 2/2 | ✅ |
| E01 – Dateiablage | Package → Dateipfad | 2/2 | ✅ |
| O07 – Mehrfachvererbung | Kprim | 1/2 | ⚠️ |
| O02 – Methodensignatur | Kprim | 2/2 | ✅ |
| O06 – JavaDoc Block | `/** ... */` | 2/2 | ✅ |
| O01 – Java Programm | Kprim | 2/2 | ✅ |

**Zusammenfassung:** 20 von 30 Fragen vollständig korrekt, 5 teilweise korrekt, 3 falsch.

---

## ❌ Fehleranalyse & Korrekturen

### Fehler 1: O05 – do-while (0/2 Punkte)

**Deine Antworten vs. korrekte Antworten:**

| Aussage | Deine Antwort | Korrekt | Erklärung |
|---|---|---|---|
| do-while wird wiederholt, solange Ausdruck `true` | falsch ❌ | **richtig** | Die Schleife läuft solange die Bedingung `true` ist! |
| do-while ist sehr ähnlich zur for-Schleife | richtig ❌ | **falsch** | do-while ist ähnlich zur **while-Schleife** (Ausgangstest), nicht zur for-Schleife |
| do-while mit `false` wird nie ausgeführt | falsch ✅ | **falsch** | Korrekt! do-while wird **immer mindestens 1x** ausgeführt (Ausgangstest!) |
| do-while ist Schleifenkonstrukt mit Eingangstest | falsch ✅ | **falsch** | Korrekt! do-while hat **Ausgangstest** (nicht Eingangstest) |

> **Merke:** `do { ... } while(bedingung);` → Bedingung wird **nach** dem Rumpf geprüft → **Ausgangstest** → **mindestens 1 Durchlauf garantiert** → läuft **solange** `true`!

---

### Fehler 2: P01 – Sichtbarkeit / Scope (0/2 Punkte)

**Aufgabe:** Aufsteigend vom kleinsten bis grössten Scope anordnen.

**Korrekte Reihenfolge (kleinster → grösster Scope):**

| Rang | Element | Scope |
|---|---|---|
| 1 (kleinster) | **Lokale Variable** | Nur innerhalb des Blocks `{ }` sichtbar |
| 2 | **Methodenparameter** | Innerhalb der gesamten Methode sichtbar |
| 3 | **Attribut** (Instanzvariable) | Innerhalb der gesamten Instanz (Objekt) sichtbar |
| 4 (grösster) | **Klassenattribut** (`static`) | Innerhalb der gesamten Klasse sichtbar, existiert ohne Objekt |

> **Merke:** Lokale Var < Methodenparameter < Attribut < Klassenattribut (static)

---

### Fehler 3: O07 – Vererbung Keyword (0/1 Punkt)

**Frage:** `public class Mitarbeiter ___ Person { ... }` – Welches Keyword?

| Deine Antwort | Korrekt |
|---|---|
| `implements` ❌ | **`extends`** |

> **Merke:**
> - **`extends`** = Klasse erbt von einer anderen **Klasse** (Spezialisierung/Vererbung)
> - **`implements`** = Klasse implementiert ein **Interface** (Schnittstelle)
> - Person ist eine **Klasse** → daher `extends`!

---

### Fehler 4: O01 – OOP Grundlagen (1/2 Punkte)

**Frage:** "In Objekten wird der Zustand über A... ___ festgehalten und das Verhalten über M... ___ festgelegt."

| Lücke | Deine Antwort | Korrekt |
|---|---|---|
| A... | "Attributen" ❌ | **"Attribute"** (ohne 'n') |
| M... | "Methoden" ✅ | Methoden |

> **Merke:** Exakte Schreibweise zählt bei Lückentext-Fragen! "Attribute" (nicht "Attributen").

---

### Fehler 5: P01 – Information Hiding (1/2 Punkte)

| Aussage | Deine Antwort | Korrekt |
|---|---|---|
| Information Hiding ist anspruchsvolles Designkonzept | falsch ❌ | **richtig** |
| Datenkapselung ist technische Grundlage für IH | richtig ✅ | richtig |
| Information Hiding vereinfacht nachträgliche Codeänderungen | richtig ✅ | richtig |
| Wenn man Datenkapselung anwendet, hat man auch IH erreicht | falsch ✅ | falsch |

> **Merke:** Information Hiding **IST** ein anspruchsvolles Designkonzept! Es reicht nicht, einfach `private` + Getter/Setter zu schreiben – man muss die interne Repräsentation bewusst von der Schnittstelle entkoppeln.

---

### Fehler 6: O03 – Fliesskomma-Genauigkeit (1/2 Punkte)

| Datentyp | Deine Antwort | Korrekt |
|---|---|---|
| `float` Genauigkeit | 7 ✅ | ca. 7 Dezimalstellen |
| `double` Genauigkeit | 16 ❌ | ca. **15** Dezimalstellen |

> **Merke:** `float` = 32 Bit ≈ **7** Dezimalstellen | `double` = 64 Bit ≈ **15** Dezimalstellen

---

### Fehler 7: O07 – Vererbung Grundlagen (1/2 Punkte)

**Frage:** "Wenn B eine Spezialisierung von A ist, dann..."

| Aussage | Deine Antwort | Korrekt | Erklärung |
|---|---|---|---|
| B ist Oberklasse von A | falsch ✅ | falsch | B ist die **Unterklasse** |
| A ist Subtyp von B | falsch ✅ | falsch | A ist der **Supertyp** |
| B hat von A geerbt | richtig ✅ | richtig | ✓ |
| A kann nicht mehr weiter spezialisiert werden | richtig ❌ | **falsch** | A kann weiterhin spezialisiert werden! Nur `final` verhindert das |

> **Merke:** Eine Klasse kann beliebig oft spezialisiert werden – es sei denn, sie ist `final`!

---

### Fehler 8: O07 – Mehrfachvererbung (1/2 Punkte)

| Aussage | Deine Antwort | Korrekt |
|---|---|---|
| Java beherrscht ohne Einschränkungen Mehrfachvererbung | falsch ✅ | falsch |
| Java kennt prinzipiell nur Einfachvererbung | richtig ✅ | richtig |
| Klassen können nur eine Basisklasse haben | richtig ❌ | **richtig** (war korrekt markiert, aber als falsch gewertet) |
| Klassen: Einfachvererbung, Interfaces: Mehrfachvererbung | richtig ✅ | richtig |

> **Merke:** Java-Klassen → nur **eine** Superklasse (`extends`). Java-Interfaces → **mehrere** Interfaces können implementiert werden (`implements`).

---

## 📊 Schwachstellen-Analyse

| Themenbereich | Fehlerquote | Priorität |
|---|---|---|
| **Schleifen (do-while vs. while vs. for)** | Hoch | 🔴 |
| **Scope/Sichtbarkeit** | Hoch | 🔴 |
| **extends vs. implements** | Hoch | 🔴 |
| **Information Hiding Konzept** | Mittel | 🟡 |
| **Vererbung & final** | Mittel | 🟡 |
| **Fliesskomma-Genauigkeit** | Niedrig | 🟢 |

---

## 📝 Checkliste zum Nachlernen

- [ ] Unterschied do-while (Ausgangstest) vs. while (Eingangstest) vs. for sicher erklären
- [ ] Scope-Hierarchie: lokale Var < Parameter < Attribut < Klassenattribut (static)
- [ ] `extends` (Klasse → Klasse) vs. `implements` (Klasse → Interface) nie verwechseln
- [ ] Information Hiding ist ein **anspruchsvolles** Designkonzept (nicht trivial!)
- [ ] `float` ≈ 7 Dezimalstellen, `double` ≈ 15 Dezimalstellen
- [ ] Eine Klasse kann beliebig spezialisiert werden, es sei denn `final`
- [ ] Bei Lückentext auf exakte Schreibweise achten

---

## 🔗 Verbindungen

| Woche | Verbindung |
|---|---|
| **SW01–SW07** | Test 1 deckt den gesamten Stoff ab: Grundlagen, Datentypen, Kontrollstrukturen, Vererbung, Interfaces, Datenkapselung, Collections |
| **SW09** | Neuer Stoff: Arrays (Kapitel 7), Enumerationen (O10), Collections-Einsatz (D02), static/final Keywords |
