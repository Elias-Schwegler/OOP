# 🛠️ Java in VS Code – Setup & Shortcuts für die OOP-Prüfung

> **Zweck:** Du bist gewohnt an IntelliJ, gehst aber mit VS Code an die Prüfung. Diese Datei erklärt, wie du in VS Code dasselbe IDE-Feeling bekommst – Extensions, Shortcuts, Tipps, Workarounds.

---

## 1. ⚡ Mandatory Extensions (in genau dieser Reihenfolge installieren)

| # | Extension | Publisher | Warum |
|---|---|---|---|
| 1 | **Extension Pack for Java** | Microsoft | Bundle: enthält die nächsten 5 automatisch (Language Support, Debugger, Test Runner, Maven, Project Manager) |
| 2 | Language Support for Java(TM) by Red Hat | Red Hat | Compiler / IntelliSense / Diagnostics (eclipse jdt unter der Haube) |
| 3 | Debugger for Java | Microsoft | Breakpoints, Step-Over, Step-Into |
| 4 | Test Runner for Java | Microsoft | JUnit 5 mit Run/Debug-Buttons im Editor |
| 5 | Maven for Java | Microsoft | Falls Übungen mit Maven kommen |
| 6 | Project Manager for Java | Microsoft | Projekt-Übersicht, Dependencies-Sicht |
| 7 | **GitLens** *(optional)* | GitKraken | Inline-Blame, History, vergleichbar mit IntelliJ Local History |
| 8 | **Error Lens** | Alexander | Zeigt Compiler-Fehler **direkt neben der Zeile** statt nur in Problems-Tab → **MUST-HAVE für Klausur** |
| 9 | **Better Comments** *(optional)* | Aaron Bond | Highlightet `// TODO:`, `// !`, `// ?` farbig |

> ⚠️ **Vor dem Test installieren!** Manche dieser Extensions laden beim ersten Java-File 30+ Sekunden Indizes – das willst du **nicht** während der Prüfung sehen.

---

## 2. 🎯 IntelliJ-Shortcuts → VS Code Mapping (die wichtigsten)

### Navigation (am häufigsten gebraucht!)

| Aktion | IntelliJ | VS Code (Default Win/Linux) | Anmerkung |
|---|---|---|---|
| **Datei suchen (by name)** | `Ctrl+Shift+N` | `Ctrl+P` | "Quick Open" – tippe Filename |
| **Symbol in Datei** | `Ctrl+F12` | `Ctrl+Shift+O` | Springt zu Methode/Feld in aktueller Datei |
| **Symbol global** | `Ctrl+Alt+Shift+N` | `Ctrl+T` | Suche Klasse/Methode im ganzen Projekt |
| **Volltextsuche** | `Ctrl+Shift+F` | `Ctrl+Shift+F` | ✅ identisch |
| **Gehe zu Definition** | `Ctrl+B` / `Ctrl+Click` | `F12` / `Ctrl+Click` | |
| **Gehe zu Implementierung** | `Ctrl+Alt+B` | `Ctrl+F12` | Bei Interface-Methoden |
| **Find Usages / Referenzen** | `Alt+F7` | `Shift+F12` | Wer ruft das auf? |
| **Zurück / Vorwärts navigieren** | `Ctrl+Alt+Left/Right` | `Alt+Left/Right` | |
| **Letzte Datei wechseln** | `Ctrl+Tab` | `Ctrl+Tab` | ✅ identisch |
| **Datei-Explorer öffnen** | `Alt+1` | `Ctrl+Shift+E` | |
| **Terminal öffnen/schliessen** | `Alt+F12` | `` Ctrl+` `` | Backtick-Taste |
| **Command Palette** | `Ctrl+Shift+A` | `Ctrl+Shift+P` | **das Power-Tool – kennt alles** |

### Editing

| Aktion | IntelliJ | VS Code | Anmerkung |
|---|---|---|---|
| **Umbenennen (Symbol)** | `Shift+F6` | `F2` | Über alle Dateien hinweg |
| **Code formatieren** | `Ctrl+Alt+L` | `Shift+Alt+F` | |
| **Imports organisieren** | `Ctrl+Alt+O` | `Shift+Alt+O` | |
| **Zeile duplizieren** | `Ctrl+D` | `Shift+Alt+Down/Up` | ⚠️ `Ctrl+D` in VS Code = Multi-Cursor (siehe unten) |
| **Zeile löschen** | `Ctrl+Y` | `Ctrl+Shift+K` | |
| **Zeile auf/ab verschieben** | `Shift+Alt+Up/Down` | `Alt+Up/Down` | |
| **Kommentar (Zeile)** | `Ctrl+/` | `Ctrl+/` | ✅ identisch |
| **Kommentar (Block)** | `Ctrl+Shift+/` | `Shift+Alt+A` | |
| **Multi-Cursor (gleiches Wort)** | `Alt+J` | `Ctrl+D` | Markiert nächstes Vorkommen |
| **Alle Vorkommen auswählen** | `Ctrl+Alt+Shift+J` | `Ctrl+Shift+L` | |

### Refactoring (kritisch für Klausur!)

| Aktion | IntelliJ | VS Code | Anmerkung |
|---|---|---|---|
| **Quick Fix / Lightbulb** | `Alt+Enter` | `Ctrl+.` | Auto-Imports, "extract method" |
| **Methode extrahieren** | `Ctrl+Alt+M` | `Ctrl+Shift+R` → "Extract Method" | Erst markieren, dann Shortcut |
| **Variable extrahieren** | `Ctrl+Alt+V` | `Ctrl+Shift+R` → "Extract Variable" | |
| **Inline** | `Ctrl+Alt+N` | `Ctrl+Shift+R` → "Inline" | |
| **Surround With** | `Ctrl+Alt+T` | `Ctrl+Shift+P` → "Surround" | try/catch, if, for |
| **Move Class** | `F6` | `F2` (über Filename) | Filename-Rename verschiebt Klasse |

### Build / Run / Debug

| Aktion | IntelliJ | VS Code | Anmerkung |
|---|---|---|---|
| **Run main** | `Shift+F10` | `F5` (Debug) / `Ctrl+F5` (Run) | Klick auf "Run" über main-Methode geht auch |
| **Debug** | `Shift+F9` | `F5` | |
| **Step Over** | `F8` | `F10` | |
| **Step Into** | `F7` | `F11` | |
| **Step Out** | `Shift+F8` | `Shift+F11` | |
| **Continue** | `F9` | `F5` | |
| **Breakpoint toggle** | `Ctrl+F8` | `F9` | |
| **Test ausführen** | `Ctrl+Shift+F10` | Klick auf "Run Test"-Button neben `@Test` | |

---

## 3. 🚀 Power-Tipps für die Prüfung

### Command Palette ist dein bester Freund
`Ctrl+Shift+P` und tippe was du willst:
- `> Java: Run Tests`
- `> Java: Generate getters and setters`
- `> Format Document`
- `> Java: Override/Implement Methods` ← **wichtig für Interface-Implementierung!**
- `> Java: Generate Constructor`
- `> Java: Generate equals() and hashCode()`

### Auto-Generate Boilerplate (Schreib weniger Code)
1. Cursor in Klassenrumpf
2. `Ctrl+.` → "Source Action" oder Command Palette `> Java: Generate ...`
3. Wähle: Constructor, equals/hashCode, toString, Getter/Setter, Override

> Das ersetzt das IntelliJ-`Alt+Insert`-Menü.

### Snippets (tippen → Tab)
| Trigger | Was es einfügt |
|---|---|
| `main` | komplette `public static void main(String[] args)` |
| `sout` | `System.out.println();` |
| `psf`  | `public static final` |
| `fori` | for-Schleife mit int-Index |
| `iter` | for-each Schleife |

### IntelliSense in Java erzwingen
- `Ctrl+Space` → manuell Auto-Complete-Liste öffnen
- `Ctrl+Shift+Space` → Parameter-Hints anzeigen (welche Argumente erwartet die Methode?)

### Fehler-Übersicht (wie IntelliJ "Problems")
- `Ctrl+Shift+M` → öffnet "Problems"-Panel (alle Fehler/Warnings im Workspace)
- Mit Error Lens (Extension): Fehler stehen **direkt neben der Zeile** – noch besser als IntelliJ

---

## 4. 🧰 Workspace-Setup für die Prüfung (5 Minuten Vorbereitung!)

### a) Java-Version prüfen
Im Terminal:
```bash
java -version    # sollte 21+ zeigen
javac -version
```

### b) settings.json (User Settings) – sinnvolle Defaults

`Ctrl+Shift+P` → "Preferences: Open User Settings (JSON)" und ergänze:

```json
{
  "java.format.settings.url": "https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml",
  "editor.formatOnSave": true,
  "editor.formatOnPaste": true,
  "editor.tabSize": 4,
  "editor.insertSpaces": true,
  "editor.bracketPairColorization.enabled": true,
  "editor.guides.bracketPairs": true,
  "java.completion.importOrder": ["java", "javax", "org", "com", ""],
  "java.saveActions.organizeImports": true,
  "java.compile.nullAnalysis.mode": "automatic",
  "java.configuration.updateBuildConfiguration": "automatic",
  "errorLens.enabledDiagnosticLevels": ["error", "warning", "info"]
}
```

### c) Tasks für schnelles Compile/Run (`.vscode/tasks.json`)
Erstelle `.vscode/tasks.json` im OOP-Ordner:

```json
{
  "version": "2.0.0",
  "tasks": [
    {
      "label": "Compile all",
      "type": "shell",
      "command": "javac",
      "args": ["-d", "out", "-sourcepath", "src", "src/**/*.java"],
      "group": { "kind": "build", "isDefault": true },
      "problemMatcher": ["$javac"]
    }
  ]
}
```
Dann: `Ctrl+Shift+B` → kompiliert alles.

---

## 5. 🐛 Debugger-Quick-Start

### Breakpoint setzen
1. Klick links neben Zeilennummer (roter Punkt erscheint)
2. ODER: Cursor auf Zeile, dann `F9`

### Conditional Breakpoint (mega nützlich!)
1. Rechtsklick auf den Breakpoint
2. "Edit Breakpoint" → Condition wie `i > 100` eingeben
3. Hält nur an, wenn Bedingung erfüllt

### Watch / Variables Panel
- **Variables** zeigt alle lokalen Variablen + `this`
- **Watch** → expressions zur Laufzeit auswerten (z.B. `liste.size()`)

### Hot-Code-Replace
VS Code Java unterstützt Hot-Code-Replace: Code im Debug-Modus ändern → speichert → JVM lädt die geänderte Methode neu, ohne neu zu starten.

---

## 6. 🆘 Wenn IntelliSense / Compiler streikt

### Symptom: "cannot find symbol" obwohl die Datei da ist
1. `Ctrl+Shift+P` → `Java: Clean Java Language Server Workspace` → **Restart and delete**
2. Warte 30s bis JDT neu indiziert
3. `Ctrl+Shift+P` → `Developer: Reload Window` (Notbremse)

### Symptom: "Project is unmanaged"
- Wenn du nur einen src-Ordner ohne pom.xml hast: das ist OK, VS Code arbeitet trotzdem (wird als "single-file mode" oder "invisible project" geführt)
- Bei Maven-Projekt: rechte Maustaste auf `pom.xml` → "Update Maven Project"

### Symptom: "Class not found" beim Run
- Stelle sicher, dass du im src-Ordner kompilierst:
  ```bash
  cd src && javac sw11/*.java && java sw11.Sw11Demo
  ```
- VS Code Run-Button macht das automatisch korrekt

---

## 7. 🎓 Klausur-spezifische Tipps

### Prüfung-Vorbereitung Checklist (2 Tage vor Test)
- [ ] Extension Pack for Java + Error Lens installiert
- [ ] `java --version` prüft (>= 21)
- [ ] Snippets, Shortcuts in [Section 2 + 3](#2--intellij-shortcuts--vs-code-mapping-die-wichtigsten) durchgegangen
- [ ] Templates in [src/pruefungsvorbereitung/](src/pruefungsvorbereitung) geöffnet und gelesen
- [ ] Bestehende Demo-Klassen einmal kompiliert + ausgeführt (so dass Cache warm ist)
- [ ] Eine Java-Datei vorbereiten und damit `Ctrl+P`, `Ctrl+T`, `F12`, `Shift+F12` testen

### Während der Prüfung
1. Erst die ganze Aufgabe lesen, dann erst Code schreiben
2. **Skelett zuerst:** Klassen + Methoden-Signaturen + leere Bodies → Compiler glücklich machen
3. Dann **Implementation** schrittweise füllen
4. Bei Refactor-Aufgabe: erst lesen / verstehen → Code-Smells identifizieren → schrittweise verbessern
5. Bei Pattern-Aufgabe: passendes Template aus [src/pruefungsvorbereitung/](src/pruefungsvorbereitung) öffnen, anpassen
6. Mindestens **EIN System.out.println** in main() → Beweis dass Code läuft
7. **Save-on-format ist deine Versicherung** gegen Style-Punkteabzug

### Was VS Code NICHT kann (Stand 2026)
| Feature | IntelliJ | VS Code | Workaround |
|---|---|---|---|
| Live Templates (eigene Snippets) | ja, Settings → Live Templates | nur User Snippets | `Ctrl+Shift+P` → "Configure User Snippets" → java.json |
| Local History (Datei-Versionen ohne Git) | ja, eingebaut | nein | GitLens approximiert es; sonst regelmässig commiten |
| Database-Tools | DataGrip integriert | nicht relevant für OOP-Test | – |
| Visual Refactor "Change Method Signature" | ja | nur via F2-Rename + manuell | für Klausur reicht `F2` + manuelle Param-Edits |

---

## 8. 📚 Eigene Snippets für die Prüfung anlegen

`Ctrl+Shift+P` → "Configure User Snippets" → "java.json":

```json
{
  "Main method": {
    "prefix": "main",
    "body": [
      "public static void main(final String[] args) {",
      "    $1",
      "}"
    ]
  },
  "Sysout": {
    "prefix": "sout",
    "body": "System.out.println($1);"
  },
  "Logger SLF4J": {
    "prefix": "logger",
    "body": "private static final Logger LOG = LoggerFactory.getLogger(${1:ClassName}.class);"
  },
  "Try-with-resources": {
    "prefix": "twr",
    "body": [
      "try (${1:Resource} ${2:res} = ${3:new $1(...)})  {",
      "    $0",
      "}"
    ]
  },
  "Equals-Hashcode skeleton": {
    "prefix": "eqhc",
    "body": [
      "@Override",
      "public boolean equals(final Object obj) {",
      "    if (this == obj) return true;",
      "    if (obj == null || getClass() != obj.getClass()) return false;",
      "    ${1:ClassName} other = (${1:ClassName}) obj;",
      "    return $0;",
      "}",
      "",
      "@Override",
      "public int hashCode() {",
      "    return java.util.Objects.hash($0);",
      "}"
    ]
  }
}
```

> Damit hast du in der Prüfung: `twr` + Tab → fertige try-with-resources Vorlage. `eqhc` + Tab → equals/hashCode-Skelett.

---

## 9. 🔗 Quick-Links innerhalb des OOP-Ordners

- [src/pruefungsvorbereitung/CHEATSHEET.md](src/pruefungsvorbereitung/CHEATSHEET.md) – Stoff-Cheatsheet
- [src/pruefungsvorbereitung/](src/pruefungsvorbereitung) – alle Templates (`_01` bis `_14`)
- [src/HOW_TO_RUN.md](src/HOW_TO_RUN.md) – Compile/Run via Terminal
- [src/sw11/](src/sw11) – Events / Observer
- [src/sw12/](src/sw12) – IO-Datenströme

---

## 10. 🚨 Day-of-Test Cheat-Sheet (Print-Out-Version)

```
NAVIGATION
  Ctrl+P          → Datei suchen
  Ctrl+Shift+O    → Symbol in Datei (Methode/Feld)
  Ctrl+T          → Symbol global
  Ctrl+Shift+F    → Volltextsuche
  F12             → Definition
  Shift+F12       → Find Usages
  Alt+Left/Right  → Vor/Zurück

EDITING
  F2              → Umbenennen
  Shift+Alt+F     → Format
  Shift+Alt+O     → Imports organisieren
  Ctrl+.          → Quick Fix / Lightbulb
  Ctrl+Shift+R    → Refactor (Extract Method/Variable)
  Alt+Up/Down     → Zeile verschieben

RUN / DEBUG
  Ctrl+F5         → Run (ohne Debug)
  F5              → Debug
  F9              → Breakpoint
  F10/F11         → Step Over / Step Into
  Shift+F11       → Step Out

POWER
  Ctrl+Shift+P    → Command Palette (kennt alles!)
  Ctrl+Space      → Auto-Complete erzwingen
  Ctrl+Shift+M    → Probleme-Tab
```

> 📌 Zum Ausdrucken: nur Section 10 ist die "auf einen Zettel"-Version. Rest ist Nachschlagewerk.
