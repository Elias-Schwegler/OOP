# 📘 OOP – SW12: IO-Datenströme

> **Modul:** Objektorientierte Programmierung (OOP) · HSLU
> **Woche:** SW12 – KW19 (05.05.2026)
> **Themen:** Eingabe-/Ausgabe-Datenströme, Datei-IO, Binary vs. Text, Properties (O13)
> **Dozent:** Roland Gisler
> **Quellen:** `O13_IP_IO-Datenströme.pdf`, `U11_EX_IO-Datenströme.pdf`, `DemoBinaryQuiz.java`, `netatmo-export-202501-202504.csv`
> **Übungen:** U11 – IO-Datenströme
> **Praxis-Code:** [src/sw12/](../../src/sw12)

---

## 🎯 Lernziele

- **Stream-Konzept** in Java verstehen (Byte- vs. Character-Streams)
- **`try-with-resources`** korrekt verwenden (Streams immer schliessen)
- **Dekorator-Pattern** anwenden: `BufferedReader(new FileReader(...))`
- Unterschied **Binary** vs. **Text** kennen (Byte-Layout, Encoding)
- **Properties-Dateien** für Konfiguration nutzen
- **CSV-Parsing** in einfachen Fällen selbst implementieren
- `IOException` als **Checked Exception** korrekt behandeln (siehe SW10)

---

## 📖 Wichtigste Begriffe

| Begriff | Definition |
|---|---|
| **Stream** | Sequenzieller Datenfluss zwischen Quelle und Senke. Lesend = `InputStream`, schreibend = `OutputStream`. |
| **Byte-Stream** | Liest/schreibt **Bytes** (8-bit). Klassen enden auf `InputStream`/`OutputStream`. |
| **Character-Stream** | Liest/schreibt **Zeichen** (16-bit Unicode). Klassen enden auf `Reader`/`Writer`. |
| **Buffered…** | Wrapper, der Daten **puffert** → 10–100× schneller bei vielen kleinen Reads/Writes. |
| **Data…Stream** | Wrapper für **primitive Typen** (`writeInt`, `readFloat`, …). Binary, fester Byte-Layout. |
| **Object…Stream** | Wrapper für **komplette Java-Objekte** (Serialisierung). Klasse muss `Serializable` sein. |
| **`Properties`** | Java-Standardklasse für `key=value`-Konfigurationen. |
| **try-with-resources** | `try (Resource r = ...) { ... }` – Resource wird **automatisch geschlossen**, auch bei Exception. |
| **Dekorator-Pattern** | Stream wickelt einen anderen Stream → Funktionalität wird "geschichtet". |
| **Encoding** | Wie Zeichen → Bytes übersetzt werden (UTF-8 ist Standard, **immer explizit angeben!**). |
| **Checked Exception** | `IOException` muss behandelt oder weitergereicht werden (`throws`). |

---

## 🧠 Konzepte & Theorie

### 1. Stream-Hierarchie auf einen Blick

```
                 INPUT (lesen)                   OUTPUT (schreiben)
                 ─────────────                   ──────────────────
BYTES (binary)   InputStream                     OutputStream
                 ├─ FileInputStream              ├─ FileOutputStream
                 ├─ BufferedInputStream          ├─ BufferedOutputStream
                 ├─ DataInputStream              ├─ DataOutputStream
                 └─ ObjectInputStream            └─ ObjectOutputStream

CHARS (text)     Reader                          Writer
                 ├─ FileReader                   ├─ FileWriter
                 ├─ BufferedReader               ├─ BufferedWriter
                 └─ InputStreamReader            └─ OutputStreamWriter

BRÜCKE           InputStreamReader               OutputStreamWriter
                 (Bytes -> Chars + Encoding)     (Chars -> Bytes + Encoding)
```

> 💡 **Faustregel:** Brauche ich Text? → `Reader`/`Writer`. Brauche ich Bytes (Bilder, Binary-Format)? → `InputStream`/`OutputStream`.

---

### 2. Dekorator-Pattern in Aktion

```java
new BufferedReader(                    // Layer 3: puffert
    new InputStreamReader(             // Layer 2: Bytes -> Chars + Encoding
        new FileInputStream(file),     // Layer 1: liest Bytes aus Datei
        StandardCharsets.UTF_8))
```

| Schicht | Aufgabe |
|---|---|
| `FileInputStream` | Liest **Bytes** roh aus der Datei |
| `InputStreamReader` | Wandelt **Bytes → Zeichen** mit angegebener Encoding |
| `BufferedReader` | Puffert + bietet `readLine()` |

> ⚠️ **Niemals ohne Buffer!** Roher `FileReader` ist 10–100× langsamer.

---

### 3. `try-with-resources` (Java 7+)

```java
// ✅ KORREKT
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
} // br wird hier AUTOMATISCH geschlossen, auch bei Exception
```

```java
// ❌ FALSCH (alte Schule, fehleranfällig)
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("data.txt"));
    // ...
} finally {
    if (br != null) br.close();   // wird oft vergessen oder falsch geschachtelt
}
```

> ⚠️ **Klausurfrage:** "Was ist `try-with-resources` und warum brauchen wir das?" → Resource wird auch bei Exception sauber geschlossen, ohne `finally`-Boilerplate.

---

### 4. Binary vs. Text – konkretes Beispiel

`DemoBinaryQuiz.java` (im SW12-Ordner) schreibt:
```java
final int value = 825_373_492;
dos.writeInt(value);
```

**In der Datei stehen 4 Bytes:**
```
0x31 0x32 0x33 0x34   ← Hex
'1'  '2'  '3'  '4'    ← ASCII-Interpretation!
```

**Erklärung:** `825_373_492` als 32-bit big-endian Hex ist genau `0x31323334`, was zufällig den ASCII-Codes für `"1234"` entspricht. Wenn man die Datei mit einem Texteditor öffnet, sieht man `1234` – aber es sind **Binärdaten**, kein Text!

> 💡 **Lehrstück:** Eine Datei hat KEINE inhärente "Codierung". Was man sieht, hängt davon ab, wie man liest.

---

### 5. Properties-Dateien

Format `sensor.properties`:
```properties
# Kommentar
sensor.id=netatmo-001
sensor.threshold.celsius=25.0
logging.level=INFO
```

```java
Properties props = new Properties();
try (var is = new FileInputStream("sensor.properties")) {
    props.load(is);
}
String id = props.getProperty("sensor.id");
float th  = Float.parseFloat(props.getProperty("sensor.threshold.celsius", "20.0"));
```

> 💡 Verwendung: i18n (`messages_de.properties`), Application-Konfig, Logging-Level (`log4j.properties`).

---

## 📊 Stream-Auswahltabelle (Klausur-Spickzettel)

| Du willst… | Nimm… |
|---|---|
| Bytes lesen aus Datei | `BufferedInputStream` über `FileInputStream` |
| Bytes schreiben in Datei | `BufferedOutputStream` über `FileOutputStream` |
| Text lesen (UTF-8) | `Files.newBufferedReader(path, UTF_8)` ← **kürzeste Variante!** |
| Text schreiben (UTF-8) | `Files.newBufferedWriter(path, UTF_8)` |
| Primitive Typen (int/float) binär | `DataOutputStream` / `DataInputStream` |
| Java-Objekt serialisieren | `ObjectOutputStream` / `ObjectInputStream` (nur wenn `Serializable`) |
| Konfiguration (key=value) | `Properties.load()` / `Properties.store()` |
| Stream zu String | `new String(in.readAllBytes(), UTF_8)` |
| Stream → Stream kopieren | `in.transferTo(out)` (Java 9+) |

---

## 📐 Code-Skelette

### Text-Datei lesen (kürzeste Variante seit Java 7+)
```java
List<String> lines = Files.readAllLines(Path.of("data.txt"), StandardCharsets.UTF_8);
```

### Text zeilenweise lesen (für grosse Dateien)
```java
try (BufferedReader br = Files.newBufferedReader(Path.of("data.txt"), StandardCharsets.UTF_8)) {
    String line;
    while ((line = br.readLine()) != null) {
        // verarbeiten
    }
}
```

### Binary schreiben + lesen
```java
// SCHREIBEN
try (DataOutputStream dos = new DataOutputStream(
        new BufferedOutputStream(new FileOutputStream("werte.bin")))) {
    dos.writeInt(werte.length);
    for (float v : werte) dos.writeFloat(v);
}
// LESEN
try (DataInputStream dis = new DataInputStream(
        new BufferedInputStream(new FileInputStream("werte.bin")))) {
    int n = dis.readInt();
    float[] werte = new float[n];
    for (int i = 0; i < n; i++) werte[i] = dis.readFloat();
}
```

### CSV einlesen (Hand-Parser)
```java
try (BufferedReader br = Files.newBufferedReader(Path.of("data.csv"), StandardCharsets.UTF_8)) {
    String line;
    boolean header = true;
    while ((line = br.readLine()) != null) {
        if (header) { header = false; continue; }
        String[] cols = line.split(",");
        // cols[0], cols[1], ...
    }
}
```

---

## ✏️ U11 – Übung: IO-Datenströme

### Aufgaben (Zusammenfassung)
1. Schreibe Temperaturwerte in eine **Binary-Datei** (`DataOutputStream`)
2. Lies sie wieder ein und vergleiche
3. Schreibe dieselben Werte als **CSV-Datei**
4. Lade eine **Properties-Datei** mit Konfiguration
5. Bonus: Parse die `netatmo-export-202501-202504.csv` und finde die wärmste Stunde

### Lösung im Repo
Komplette Implementierung in [src/sw12/](../../src/sw12):

| Datei | Zweck |
|---|---|
| [`TemperaturFileIO.java`](../../src/sw12/TemperaturFileIO.java) | Read/Write Binary + CSV |
| [`TemperaturProperties.java`](../../src/sw12/TemperaturProperties.java) | Konfiguration via Properties |
| [`NetatmoCsvParser.java`](../../src/sw12/NetatmoCsvParser.java) | CSV-Parser für Netatmo-Export |
| [`Sw12Demo.java`](../../src/sw12/Sw12Demo.java) | Demo: alles zusammen |

### Demo ausführen
```bash
cd "C:/Users/elias/Documents/HSLU_MODULE/Semester_2/OOP/src"
javac sw12/*.java
java sw12.Sw12Demo
```

### Erwarteter Output
```
=== SW12 Demo: IO-Datenstroeme ===

Schreibe Demo-Dateien nach: C:\Users\elias\AppData\Local\Temp\oop-sw12-...

[BINARY]
  Geschrieben: [293.15, 295.65, 291.1, 299.0]
  Gelesen:     [293.15, 295.65, 291.1, 299.0]
  Datei-Groesse: 20 Bytes (4 Byte * n + 4 Byte count)

[CSV]
  Geschrieben: [293.15, 295.65, 291.1, 299.0]
  Gelesen:     [293.15, 295.65, 291.1, 299.0]
  Datei-Groesse: 38 Bytes (deutlich groesser, weil Text)

[PROPERTIES]
  Sensor-ID:        netatmo-001
  Threshold (C):    25.0
  Threshold-Wert mit Fallback (fehlt): 99.0
```

---

## ⚠️ Prüfungsrelevante Hinweise

### Klausurfragen, die fast sicher kommen
1. **Warum `try-with-resources`?** → automatisches Schliessen, auch bei Exception, kein `finally`-Boilerplate
2. **Warum `BufferedXxx`?** → Performance: einzelne `read()`/`write()`-Calls sind teuer, Buffer reduziert auf einen Block-Zugriff
3. **Was passiert, wenn man die Datei aus `DemoBinaryQuiz` mit Notepad öffnet?** → Man sieht `1234`, weil die Bytes des Integers zufällig den ASCII-Codes von `'1'..'4'` entsprechen (siehe Theorie)
4. **Was ist der Unterschied zwischen `Reader` und `InputStream`?** → Reader = Zeichen (16-bit), InputStream = Bytes (8-bit). Brücke: `InputStreamReader`.
5. **Warum Encoding immer explizit angeben?** → Ohne Encoding nutzt Java die Plattform-Default-Encoding, das ist auf Windows z.B. CP1252 statt UTF-8 → Bugs bei Umlauten / non-ASCII

### Häufige Fehler
- Stream nicht schliessen → File-Handle-Leak (Windows: Datei kann nicht gelöscht werden)
- `FileReader` ohne `BufferedReader` → langsam
- Encoding nicht angeben → Umlaute zerschossen je nach Plattform
- Binary-Datei mit Text-Tools öffnen → "merkwürdige Zeichen" (eigentlich korrekt, aber falsch interpretiert)
- `IOException` einfach mit `catch (Exception e) {}` schlucken → Bug-Friedhof

### Encoding-Cheatsheet
| Plattform | Default-Encoding (vorsicht!) |
|---|---|
| Windows DE | CP1252 (Westeuropa) |
| macOS / Linux | UTF-8 |
| Java seit 18 | UTF-8 (default geändert!) |

> 💡 **Immer `StandardCharsets.UTF_8` explizit angeben** – sonst Bugs auf alten Java-Versionen oder fremden Maschinen.

---

## 🔗 Verbindung zu vorherigen / folgenden Wochen

- **SW10 (Exceptions):** `IOException` ist eine Checked Exception – hier wird das Konzept aus SW10 angewendet (`throws IOException`, try-catch).
- **SW11 (Events):** Ein Listener kann seinen Output in eine Datei schreiben → kombiniert SW11 + SW12.
- **SW13–SW15:** Vermutlich UI / Frameworks – IO als Persistenz-Layer wird dort wieder gebraucht.
- **Templates:** [`_14_IO_Streams.java`](../../src/pruefungsvorbereitung/_14_IO_Streams.java) – generische Vorlage für Klausur.
