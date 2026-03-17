# 🤖 OOP Wochen-Zusammenfassung – Prompt-Template

> **Zweck:** Diesen Prompt bei jeder neuen Semesterwoche verwenden, damit der Agent automatisch eine optimale Prüfungszusammenfassung für **Objektorientierte Programmierung (OOP, HSLU)** erstellt.

---

## Prompt (Copy-Paste für jede neue Woche)

```
Erstelle eine umfassende Prüfungszusammenfassung für die aktuelle Semesterwoche (SW XX) des Moduls "Objektorientierte Programmierung" (OOP, HSLU).

Das Ausgabeformat ist ein Markdown-File namens ZUSAMMENFASSUNG_SWXX.md im entsprechenden SW-Ordner.

### Quelldateien im Wochenordner:
- **Kapitel XX - [Thema].pdf** → Lehrbuchkapitel (Theorie & Konzepte)
- **OXX_IP_[Thema].pdf** → Input-Präsentation (Vorlesung)
- **AXX_IP_[Thema].pdf** → Zusätzliche Input-Präsentation (falls vorhanden)
- **UXX_EX_[Thema].pdf** → Übungsaufgaben (Exercises)
- **OFWJ-chapterXX.zip** → BlueJ-Projektdateien (Quellcode zu den Übungen)
- **OFWJ-chapterXX-solution.zip** → BlueJ-Musterlösungen (falls vorhanden)

Alle Dateien durchlesen und Inhalte integrieren.

### Struktur & Inhalt (in dieser Reihenfolge):

1. **Header**: Modulname (OOP), Wochennummer (SW XX), Thema
2. **🎯 Lernziele**: Aus den Input-Präsentationen und Übungen abgeleitete Lernziele
3. **📖 Wichtigste Begriffe**: Tabelle mit Begriff + Definition aller neuen OOP-Konzepte (Deutsch & englischer Fachbegriff)
4. **📐 Konzepte & Prinzipien**: Alle zentralen OOP-Konzepte der Woche:
   - Formale Definition / UML-Darstellung
   - Intuitive Erklärung in eigenen Worten
   - Zusammenhang mit den vier OOP-Grundprinzipien (Abstraktion, Kapselung, Vererbung, Polymorphismus)
   - Konkrete Java-Beispiele zur Veranschaulichung
5. **☕ Java-Syntax & Sprachkonstrukte**: Kompakte Übersicht aller relevanten Java-Elemente:
   - Schlüsselwörter, Modifikatoren, Annotationen
   - Syntax-Regeln und Konventionen
   - Häufige Fehlerquellen bei der Syntax
6. **📊 Vergleiche & Klassifizierungen**: Tabellen die Konzepte, Muster oder Ansätze gegenüberstellen (z.B. Komposition vs. Vererbung, ArrayList vs. Array, etc.)
7. **💻 Code-Beispiele (Java)**: Alle relevanten Implementierungen aus den Kapiteln, Inputs und Musterlösungen:
   - Kommentare auf Deutsch
   - Erklärung des OOP-Konzepts vor dem Code
   - Code aus den Musterlösungen (solution.zip) bevorzugen
   - Vollständige Klassen-Beispiele wo sinnvoll
   - JUnit-Testbeispiele wenn vorhanden
8. **📋 UML-Diagramme**: Klassendiagramme, Objektdiagramme oder Sequenzdiagramme (als Text/Mermaid) zur Visualisierung der Beziehungen
9. **✏️ Übungsaufgaben-Zusammenfassung**: Tabelle aller Übungen mit:
   - Aufgabennummer
   - Thema / Konzept
   - Lösungsansatz (Kurzform)
   - Typische Stolpersteine
10. **⚠️ Prüfungsrelevante Hinweise**:
    - Typische Programmieraufgaben und wie man sie erkennt
    - Häufige Fehlerquellen und Fallstricke in Java
    - Refactoring-Tipps (der Lösungsweg/Refactoring wird mitbewertet!)
    - Design-Patterns und Best Practices die man kennen muss
    - Wichtige Klassen aus der Java-Standardbibliothek
11. **🔗 Verbindung zu vorherigen/folgenden Wochen**: Rückbezug und Vorausschau:
    - Wie baut der Stoff auf?
    - Welche Konzepte werden später wieder gebraucht?

### Wichtige Regeln:
- **Sprache:** Deutsch (Java-Fachbegriffe und Schlüsselwörter dürfen Englisch bleiben)
- **Code:** Ausschliesslich Java (keine anderen Sprachen)
- **Quellen:** Alle Dateien im SW-Ordner durchlesen (Kapitel-PDF, Input-PDFs, Übungs-PDF, ZIP-Projekte)
- **Code-Formatierung:** Java-Syntax-Highlighting in Markdown verwenden (```java)
- **UML:** Mermaid-Syntax für Diagramme verwenden wo möglich
- **Emojis** als Section-Icons verwenden für schnelles Scannen
- **Tabellen** bevorzugen für Vergleiche und Übersichten
- **Prüfungsformat beachten:** Die MEP ist eine elektronische Programmierprüfung (Java in IDE), open-book, ohne KI – die Zusammenfassung muss also besonders auf praktische Programmierfähigkeiten ausgerichtet sein
- Das File soll so vollständig sein, dass man damit alleine die Prüfung bestehen kann
- **Refactoring** ist prüfungsrelevant: Code-Verbesserungs-Tipps immer erwähnen
- ZUSAMMENFASSUNG aus vorherigen Wochen lesen für Querverweise
```

---

## Themenübersicht pro Woche

| Woche | KW | Thema | Schlüsselkonzepte |
|-------|----|-------|-------------------|
| SW 01 | KW08 | Objekte und Klassen | Objekte, Klassen, Methoden, Instanzvariablen, BlueJ |
| SW 02 | KW09 | Objektinteraktion | Klassendefinitionen, Felder, Konstruktoren, Methoden, Parameter |
| SW 03 | KW10 | Objektsammlungen | Gruppierung, Sammlungen, Schleifen, Iteratoren, Arrays |
| SW 04 | KW11 | Schnittstellen & Datenkapselung | Interfaces, abstrakte Klassen, Zugriffsmodifizierer, Information Hiding |
| SW 05 | KW12 | Vererbung & Entwicklungsumgebung | Vererbung, extends, super, Subtyping, Ersetzbarkeit, Polymorphe Variablen, IDE |
| SW 06 | KW13 | Entwurf von Klassen | Kopplung, Kohäsion, Kapselung, Verantwortlichkeiten, Refactoring |
| SW 07 | KW14 | Vererbung | Superklassen, Subklassen, Vererbungshierarchien, Überschreiben |
| SW 08 | KW15 | Polymorphismus | Dynamische Typen, Überschreiben, Polymorphe Methodenaufrufe |
| SW 09 | KW16 | Abstrakte Klassen & Interfaces | Abstrakte Methoden, Interfaces, Mehrfachvererbung |
| SW 10 | KW17 | Fehlerbehandlung | Exceptions, try-catch, Checked/Unchecked, Fehlerstrategien |
| SW 11 | KW18 | Weiterführende Konzepte | Generics, Collections Framework, Enumerationen |
| SW 12 | KW19 | Entwurfsmuster | Design Patterns, Observer, Strategy, Singleton, Factory |
| SW 13 | KW20 | GUI & Ereignisbehandlung | Swing/JavaFX, Event-Handling, Listener, Layout-Manager |
| SW 14 | KW21 | Streams & Lambdas | Funktionale Programmierung, Lambda-Ausdrücke, Stream-API |
| SW 15 | KW22 | Wiederholung & Prüfungsvorbereitung | Gesamtübersicht, Prüfungsaufgaben, Transferbeispiele |

> **Hinweis:** Die Themen oben sind vorläufig basierend auf dem Lehrbuch "Objects First with Java" und den verfügbaren Wochenordnern. Bitte anhand der tatsächlichen PDFs im jeweiligen Ordner verifizieren und anpassen.

---

## Ordnerstruktur-Erwartung

```
OOP/
├── Unterlagen zum Unterricht/
│   ├── SW01 - KW08/
│   │   ├── Kapitel 01 - Objekte und Klassen.pdf   (Lehrbuchkapitel)
│   │   ├── O01_IP_Objektorientierung.pdf           (Input-Präsentation)
│   │   ├── A01_IP_Einfuehrung_26FS.pdf             (Zusatz-Input)
│   │   ├── U01_EX_EinführungObjektorientierung.pdf (Übungen)
│   │   ├── OFWJ-chapter01.zip                      (BlueJ-Projekte)
│   │   ├── OFWJ-chapter01-solution.zip             (Musterlösungen)
│   │   └── ZUSAMMENFASSUNG_SW01.md                 ← Output
│   ├── SW02 - KW09/
│   │   ├── Kapitel 02 - ...pdf
│   │   ├── O02_IP_...pdf
│   │   ├── U02_EX_...pdf
│   │   └── ZUSAMMENFASSUNG_SW02.md                 ← Output
│   ├── ...
│   └── SW15 - KW22/
│       └── ZUSAMMENFASSUNG_SW15.md                 ← Output
├── Zusatzmaterial/
│   ├── Anhang A - Arbeiten mit BlueJ-Projekten.pdf
│   ├── Anhang B - Datentypen in Java.pdf
│   ├── Anhang C - Operatoren.pdf
│   ├── Anhang D - Kontrollstrukturen in Java.pdf
│   ├── Anhang E - Java ohne BlueJ.pdf
│   ├── Anhang F - Benutzung des Debuggers.pdf
│   ├── Anhang G - Testwerkzeuge für Modultests mit JUnit.pdf
│   ├── Anhang H - Werkzeuge für die Teamarbeit.pdf
│   ├── Anhang I - Javadoc.pdf
│   ├── Anhang J - Quelltextkonventionen.pdf
│   ├── Anhang K - Wichtige Bibliotheksklassen.pdf
│   ├── UML-Notationsübersicht-2.5.pdf
│   └── OOP_JavaDevelopmentManual_jdk25.pdf
├── MEP Infos/
│   └── MEP_Info.txt
└── Prompt.md  ← Diese Datei
```

---

## Hinweise für den Agent

- Zuerst den **SW-Ordner** der aktuellen Woche im Pfad `Unterlagen zum Unterricht/` auslesen
- **ZIP-Dateien** entpacken (`Expand-Archive` in PowerShell) und Java-Quellcode lesen (BlueJ-Projekte)
- **Musterlösungen** (solution.zip) bevorzugt für Code-Beispiele verwenden
- ZUSAMMENFASSUNG aus vorherigen Wochen lesen für **Querverweise**
- Bei Wochen ohne Solution-ZIP: Code-Beispiele aus den Kapitel-PDFs extrahieren
- **Anhänge** aus dem Zusatzmaterial-Ordner referenzieren wo relevant (z.B. Anhang B für Datentypen, Anhang C für Operatoren)
- **UML-Diagramme** aus den Kapitel-PDFs als Mermaid-Diagramme nachbauen
- **Prüfungsrelevanz:** Die MEP ist eine Programmierprüfung → Fokus auf lauffähigen, korrekten Java-Code und Refactoring-Fähigkeiten
- **Java-Version:** JDK 25 (gemäss OOP_JavaDevelopmentManual_jdk25.pdf)

### 📄 PDF-Lesevorgang (WICHTIG!)

`view_file` kann **keine PDFs** lesen und der **Browser kann keine lokalen Datei-URLs** öffnen. Stattdessen **PyMuPDF** (`fitz`) verwenden, das bereits installiert ist:

#### Schritt 1: Text aus PDFs extrahieren
```python
python -c "
import fitz
import os
os.makedirs(r'C:\tmp\pdf_text', exist_ok=True)
pdfs = {
    'name': r'Pfad\zur\Datei.pdf',
}
for name, path in pdfs.items():
    doc = fitz.open(path)
    text = ''
    for i, page in enumerate(doc):
        text += f'=== PAGE {i+1} ===\n{page.get_text()}\n'
    with open(os.path.join(r'C:\tmp\pdf_text', f'{name}.txt'), 'w', encoding='utf-8') as f:
        f.write(text)
    print(f'{name}: {len(doc)} pages')
    doc.close()
"
```
→ Dann die `.txt`-Dateien mit `view_file` lesen.

#### Schritt 2: PDF-Seiten als Bilder extrahieren (für Diagramme/UML)
```python
python -c "
import fitz
import os
os.makedirs(r'C:\tmp\pdf_images', exist_ok=True)
pdfs = {
    'name': r'Pfad\zur\Datei.pdf',
}
for name, path in pdfs.items():
    doc = fitz.open(path)
    for i, page in enumerate(doc):
        pix = page.get_pixmap(dpi=200)
        pix.save(os.path.join(r'C:\tmp\pdf_images', f'{name}_page{i+1:02d}.png'))
    print(f'{name}: {len(doc)} pages converted')
    doc.close()
"
```
→ Dann die `.png`-Dateien mit `view_file` ansehen (UML-Diagramme, Code-Listings, Abbildungen).

#### Workflow-Zusammenfassung:
1. **Alle PDFs im SW-Ordner identifizieren** (via `list_dir`)
2. **Text extrahieren** (PyMuPDF → `.txt` in `C:\tmp`)
3. **Bilder extrahieren** (PyMuPDF → `.png` in `C:\tmp`) für Diagramme
4. **ZIPs entpacken** (`Expand-Archive`) und `.java`-Dateien mit `view_file` lesen
5. **Vorherige Zusammenfassungen lesen** für Querverweise
6. **Zusammenfassung schreiben** als `ZUSAMMENFASSUNG_SWXX.md`
