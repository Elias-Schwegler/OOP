# Probeprüfung – Raumverwaltung (Original-MEP FS22)

Diese 5-teilige Aufgabe ist die **echte OOP-Modulendprüfung vom FS22** (je 18 Punkte/Teil).
Beste Vorbereitung auf die MEP morgen!

## Struktur

```
Probepruefung/
├── Probepruefung_ungeloest/
│   └── AUFGABE_Raumverwaltung_FS22.md   ← Aufgabenstellung (Teil 1–5)
└── Probepruefung_geloest/
    └── ch/hslu/oop/rv/                   ← Referenzlösung (erst SELBST versuchen!)
```

## So gehst du vor

1. **Lies** `Probepruefung_ungeloest/AUFGABE_Raumverwaltung_FS22.md`.
2. **Löse** Teil für Teil selbst — am besten direkt im Exam-Template `../../OOP_MEP_Probe/`
   (Package `ch.hslu.oop.rv`). Stoppuhr: ~20 Min pro Teil → realistische MEP-Bedingungen.
3. **Vergleiche** danach mit `Probepruefung_geloest/`.

## Referenzlösung kompilieren/laufen lassen

Die Lösung nutzt SLF4J, JUnit 6, AssertJ, EqualsVerifier — also im Maven-Projekt ausführen:

```bash
# Klassen in dein Exam- oder OOP-Projekt unter src/main/java/ch/hslu/oop/rv/ kopieren,
# Tests unter src/test/java/ch/hslu/oop/rv/ , dann:
mvn test
```

> ⚠️ **Selbstdisziplin:** Schau die Lösung erst an, NACHDEM du es selbst versucht hast.
> Der Lerneffekt kommt vom eigenen Lösen, nicht vom Lesen.
