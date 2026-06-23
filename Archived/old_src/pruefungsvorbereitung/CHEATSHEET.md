# 📘 OOP Prüfungsvorbereitung – Cheatsheet

> **Test-Format:** Online Test + Bilat (mündlich), Inputs O01–O10, P01, E01
> **Woche der Prüfung:** Voraussichtlich Semester-Ende
> **Erlaubt:** Open-Book, **keine elektronischen Hilfsmittel!**

---

## 🎯 Die 10 wichtigsten Dinge die du auswendig können musst

### 1. Klassenrumpf-Reihenfolge (O02)

```
1. Attribute          (private, dann protected, dann package, dann public – selten)
2. Konstruktoren
3. Öffentliche Methoden (public)
4. Übrige Methoden    (protected, package)
5. Private Methoden
```

> ⚠️ **Prüfungsfrage!** Die Reihenfolge kommt regelmässig.

---

### 2. Scope-Hierarchie (P01)

```
Lokale Variable < Methodenparameter < Attribut < Klassenattribut (static)
       ↓                 ↓                 ↓                ↓
   nur im Block    ganze Methode     ganze Instanz     ganze Klasse
```

> ⚠️ **Test 1 Fehler!** Du hattest das falsch – jetzt auswendig lernen.

---

### 3. Zugriffsmodifizierer (P01)

| Keyword | UML | Sichtbar in... |
|---|---|---|
| `public` | `+` | Überall |
| `private` | `-` | Nur eigene Klasse |
| `protected` | `#` | Eigene Klasse + Subklassen + Package |
| *(kein Keyword)* | `~` | Gleiches Package |

**Faustregel:** So verschlossen wie möglich, so offen wie nötig.

---

### 4. `extends` vs. `implements` (O07)

```java
class Mitarbeiter extends Person { ... }          // Klasse erbt von Klasse
class Motor implements Switchable { ... }         // Klasse implementiert Interface
class Auto extends Vehicle implements Switchable { } // BEIDES möglich
```

> ⚠️ **Test 1 Fehler!** Du hattest `implements Person` geschrieben – korrekt ist `extends Person`.

**Merke:** Java kennt nur **Einfachvererbung** für Klassen (eine Basisklasse), aber **Mehrfachvererbung** bei Interfaces.

---

### 5. Die `this`-Referenz (O02)

| Syntax | Bedeutung |
|---|---|
| `this.name` | Attribut auf aktuellem Objekt |
| `this.methode()` | Methode auf aktuellem Objekt |
| `this(...)` | **Anderen Konstruktor** derselben Klasse aufrufen |
| `super()` | Konstruktor der **Oberklasse** aufrufen |

> ⚠️ **Nicht** `this.super()` – das gibt's nicht!

---

### 6. `==` vs. `equals()` (O09)

```java
String s1 = new String("Hallo");
String s2 = new String("Hallo");

s1 == s2        // false! (Referenzvergleich)
s1.equals(s2)   // true!  (Inhaltsvergleich)
```

**Goldene Regel:** Wer `equals()` überschreibt, **MUSS** auch `hashCode()` überschreiben.

Contract:
- `a.equals(b)` → `a.hashCode() == b.hashCode()`
- `a.hashCode() != b.hashCode()` → `!a.equals(b)`

---

### 7. Datenkapselung vs. Information Hiding (P01)

| | Datenkapselung | Information Hiding |
|---|---|---|
| **Was** | `private` + Getter/Setter | Interne Repräsentation verstecken |
| **Beispiel** | `Temperatur.celsius` privat | Intern in Kelvin, extern in Celsius |
| **Schwierigkeit** | Technisch einfach | **Anspruchsvolles Designkonzept!** |

> ⚠️ **Test 1 Fehler!** „IH ist anspruchsvoll" = **richtig**!

---

### 8. Interface vs. Abstrakte Klasse (O06)

| Eigenschaft | Interface | Abstrakte Klasse |
|---|---|---|
| Keyword | `interface` | `abstract class` |
| Instanziieren | ❌ | ❌ |
| Konstruktor | ❌ | ✅ |
| Attribute | nur `static final` | normale Attribute möglich |
| Mehrfachvererbung | ✅ (mehrere implementieren) | ❌ (nur eine erweitern) |
| Use Case | **„kann..."** (Fähigkeit) | **„ist ein..."** (Ist-Beziehung) |

---

### 9. Elementare Datentypen (O03)

Java hat **8 elementare Datentypen**:

| Typ | Bits | Bereich |
|---|---|---|
| `byte` | 8 | -128 bis 127 |
| `short` | 16 | -32'768 bis 32'767 |
| **`int`** | 32 | ~±2.1 Mrd (Default für Ganzzahl-Literale!) |
| `long` | 64 | ±9.2 * 10¹⁸ |
| `float` | 32 | **~7** Dezimalstellen |
| `double` | 64 | **~15** Dezimalstellen |
| `boolean` | 1 | true/false |
| `char` | 16 | Unicode |

> ⚠️ **Test 1 Fehler!** `double` ist ~15 Stellen, nicht 16!

---

### 10. Schleifen-Zusammenfassung

| Schleife | Test | Min. Durchläufe | Einsatz |
|---|---|---|---|
| `while` | Eingang | **0** | Unbekannte Anzahl |
| `do-while` | **Ausgang** | **1** | Mind. 1 Durchlauf nötig |
| `for` | Eingang | 0 | Bekannte Anzahl / Index |
| `for-each` | – | 0 | Collections durchlaufen |

> ⚠️ **Test 1 Fehler:** do-while = Ausgangstest, läuft solange Bedingung true!

---

## 🧠 Typische Fallen

| Situation | Falsch | Richtig |
|---|---|---|
| Float-Vergleich | `f == 1.0f` | `f >= 1.0f` (Rundungsfehler!) |
| Array-Index | `arr[arr.length]` | `arr[arr.length - 1]` |
| Array vs Collection Länge | `arr.size()` | `arr.length` (Attribut!) |
| Switch fall-through | kein `break` | **immer `break`** oder `->` |
| Overriding signature | `equals(Person p)` | `equals(Object obj)` |
| compareTo Overflow | `return a - b;` | `return Integer.compare(a, b);` |

---

## 📂 Template-Dateien in diesem Ordner

| Datei | Inhalt |
|---|---|
| `01_KlassenStruktur.java` | Korrekter Klassenaufbau mit Konventionen |
| `02_Immutable.java` | `final` Klasse + Defensive Kopien |
| `03_EqualsHashCode.java` | equals/hashCode Contract perfekt implementiert |
| `04_Comparable_Comparator.java` | Natürliche vs. externe Ordnung |
| `05_InterfaceVsAbstract.java` | Beide Muster im Vergleich |
| `06_EnumMuster.java` | Enum mit Attributen, Methoden, switch |
| `07_Vererbung.java` | extends, super, polymorphe Variablen |
| `08_Collections.java` | List/Set/Map/Iterator korrekt verwenden |
| `09_Arrays.java` | Arrays mit for-Schleifen, Maximum finden |

---

## 📅 Lernplan (letzte Woche vor Prüfung)

| Tag | Fokus |
|---|---|
| **Mo** | CHEATSHEET.md auswendig, Template 01 + 02 durchgehen |
| **Di** | Template 03 + 04 (equals/hashCode + Compare) |
| **Mi** | Template 05 + 06 (Interface/Abstract + Enum) |
| **Do** | Template 07 + 08 (Vererbung + Collections) |
| **Fr** | Template 09 + Online-Tests der Vorsemester durchspielen |
| **Sa** | Schwachstellen aus Test 1 (8 Fehler → CHEATSHEET.md oben) |
| **So** | Ruhig durchatmen, CHEATSHEET nochmal überfliegen |
