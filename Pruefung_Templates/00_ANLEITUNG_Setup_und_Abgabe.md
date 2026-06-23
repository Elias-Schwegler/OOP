# 🛠️ Setup-Test & ILIAS-Probe-Abgabe (HEUTE machen!)

Ziel: Vor der MEP sicherstellen, dass **(A)** dein IntelliJ + Maven sauber laufen und **(B)** die
elektronische ILIAS-Abgabe bei dir funktioniert. Beides UNBEDINGT heute testen!

---

## ✅ Teil A — IntelliJ-Setup-Test (5 Min)

### A1. KI ist deaktiviert (MEP-Pflicht!)
- [ ] `Full Line Code Completion` Plugin **disabled** (Settings → Plugins → Installed)
- [ ] `Editor → General → Inline Completion` → "Enable local Full Line completion" **aus**
- [ ] Falls JetBrains AI Widget oben in der Toolbar sichtbar: Rechtsklick → Disable AI Assistant
- [ ] Sicherheits-Check: tippe Code → es darf **kein** grauer KI-Vorschlag (ganze Zeile) erscheinen.
      *(Normale Autovervollständigung von bekannten Methoden/Variablen ist OK und erlaubt.)*

### A2. Maven läuft
Öffne ein Terminal (in IntelliJ unten, oder PowerShell) und prüfe:
```bash
mvn --version    # muss >= 3.8.8 sein (du hast 3.9.12)
java --version   # JDK 25
```

### A3. Test läuft (im Exam-Template)
1. Öffne das Projekt `OOP_MEP_Probe` (liegt neben `OOP/` in `Semester_2/`).
2. Klick „Load Maven Project" falls gefragt.
3. Öffne `src/test/java/ch/hslu/demo/PointTest.java` → grünes ▶ neben der Klasse → **Run**.
4. Erwartung: **grün, Tests passed**.

Wenn A1–A3 ✅ → IntelliJ ist prüfungsbereit.

---

## ✅ Teil B — ILIAS-Probe-Abgabe (10 Min, HEUTE!)

> Die Abgabe läuft genau so wie in der echten MEP. Quelle: `MEP Infos/oop-fs26-test-e001-Name.Vorname.pdf`
> ILIAS Test-Abgabe: https://elearning.hslu.ch/ilias/goto.php/exc/7182388

### B1. Exam-Template vorbereiten
Dein Template liegt schon entpackt unter `OOP_MEP_Probe/`. Name + Email im `pom.xml` sind bereits
ausgefüllt. **Eine Sache fehlt noch — deine ILIAS-UID:**

1. Öffne `OOP_MEP_Probe/pom.xml`.
2. Suche **Zeile 77**:
   ```xml
   <ilias.uid>xxx</ilias.uid>
   ```
3. Ersetze `xxx` durch deinen **ILIAS-Benutzernamen (UID)**.
   *(Findest du in ILIAS oben rechts unter deinem Profil, oder es ist meist der Teil deiner
   HSLU-Login-Kennung. Im Zweifel im ILIAS-Profil nachschauen.)*

> Kontrolle Zeile 48/50 (sollten schon stimmen):
> `<name>Elias Schwegler</name>` und `<email>elias.schwegler@stud.hslu.ch</email>`

### B2. Bauen → Abgabe-ZIP erzeugen
Im Terminal im Ordner `OOP_MEP_Probe`:
```bash
mvn clean package
```
- [ ] Muss mit **`BUILD SUCCESS`** enden.
- [ ] Im Ordner `OOP_MEP_Probe/target/` liegt jetzt **`oop_exam-26fs-<deineUID>.zip`**.
      *(Solange UID = `xxx`, heisst die Datei `oop_exam-26fs-xxx.zip` — daran erkennst du, dass du
      die UID noch setzen musst!)*

### B3. Auf ILIAS hochladen
1. Öffne https://elearning.hslu.ch/ilias/goto.php/exc/7182388 (Test-Abgabe MEP).
2. Lade die ZIP aus `target/` hoch.
3. Prüfe, dass die Abgabe als „abgegeben" erscheint.

Wenn B1–B3 ✅ → **Abgabe funktioniert.** Du weisst jetzt den genauen Ablauf für morgen.

---

## 🎯 Ablauf in der echten MEP (zur Erinnerung)
Pro Aufgabe: **lösen → `mvn clean package` (BUILD SUCCESS) → ZIP aus `target/` auf ILIAS abgeben**.
Du gibst dasselbe Projekt mehrfach ab (nach jeder Aufgabe) — der Dozent sieht die Fortschritte im Diff.

## ❗ Wenn etwas nicht klappt
- `BUILD FAILURE`? → Lies die erste rote Fehlermeldung. Oft JavaDoc-Fehler oder Syntax.
- Maven nicht gefunden? → neues Terminal öffnen (PATH wurde gesetzt).
- Bei ILIAS-Problemen: **Forum** (heute, nicht morgen!).
