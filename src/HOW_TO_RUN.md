# OOP Übungen ausführen (Terminal / VS Code)

> Java 25 ist installiert (`java 25.0.2`, `javac 25.0.2`).

## Schnellstart

Alle Befehle im **VS Code Terminal** ausführen.
Zuerst in den `src`-Ordner navigieren:

```bash
cd "C:/Users/elias/Documents/HSLU_MODULE/Semester_2/OOP/src"
```

---

## 1. Demo-Klassen kompilieren & ausführen

### SW05 ausführen

```bash
javac sw03/Point.java sw04/*.java sw05/*.java
java sw05.Sw05Demo
```

### SW06 ausführen

```bash
javac sw03/Point.java sw04/*.java sw05/CountingSwitchable.java sw05/CountingMotor.java sw05/Named.java sw06/*.java
java sw06.Sw06Demo
```

> **Hinweis:** SW06 importiert Klassen aus sw04 und sw05, daher muessen diese mitkompiliert werden.

---

## 2. Einzelne Klasse kompilieren & testen

Falls du nur eine bestimmte Datei bearbeiten und schnell testen willst:

```bash
# Alles auf einmal kompilieren (einfachste Variante)
javac sw03/*.java sw04/*.java sw05/*.java sw06/*.java

# Dann die gewuenschte Demo starten
java sw05.Sw05Demo
java sw06.Sw06Demo
```

---

## 3. JUnit-Tests (SW06)

Die Test-Dateien (`MaxUtilTest.java`, `CalculatorTest.java`) brauchen **JUnit 5 auf dem Classpath**. Im einfachen Terminal-Setup geht das so:

### Option A: Maven-Projekt verwenden (empfohlen)

Die Übungsmaterialien enthalten ein Maven-Template (`oop_maven_template`). Wenn du das verwendest, liegen Tests unter `src/test/java` und du fuehrst sie aus mit:

```bash
mvn test
```

### Option B: JUnit-JAR manuell herunterladen

Falls du ohne Maven arbeitest:

1. Lade `junit-platform-console-standalone-1.11.4.jar` herunter
   (von https://mvnrepository.com/artifact/org.junit.platform/junit-platform-console-standalone)

2. Lege die JAR z.B. in den `src`-Ordner

3. Kompilieren:
```bash
javac -cp ".;junit-platform-console-standalone-1.11.4.jar" sw06/*.java
```

4. Tests ausfuehren:
```bash
java -jar junit-platform-console-standalone-1.11.4.jar --class-path . --scan-class-path
```

### Option C: Nur die Demo-Klasse nutzen (ohne JUnit)

Die `Sw06Demo.java` demonstriert alle Aufgaben ohne JUnit. Die Test-Dateien sind fuer die IDE (IntelliJ/Eclipse) gedacht und koennen dort direkt per Rechtsklick ausgefuehrt werden.

---

## 4. Haeufige Fehler

| Problem | Loesung |
|---------|---------|
| `error: package sw04 does not exist` | Du musst aus dem `src`-Ordner kompilieren, nicht aus einem Unterordner |
| `error: class found on application class path` | Alte `.class`-Dateien loeschen: `del /s *.class` (oder `find . -name "*.class" -delete` in Git Bash) |
| `error: package org.junit.jupiter does not exist` | JUnit nicht auf Classpath → verwende Option A oder B oben, oder ignoriere die Test-Dateien und nutze `Sw06Demo` |

---

## 5. Aufräumen (`.class`-Dateien loeschen)

Nach dem Testen kannst du die kompilierten Dateien loeschen:

```bash
# Git Bash / Unix
find . -name "*.class" -delete

# Windows CMD
del /s *.class

# PowerShell
Get-ChildItem -Recurse -Filter *.class | Remove-Item
```
