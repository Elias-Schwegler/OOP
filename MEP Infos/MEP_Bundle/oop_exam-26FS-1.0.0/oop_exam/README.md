# Java Projekt für Examen (eMEP) - FS26

## Zweck
Dieses Projekt wird für elektronische Examen (eMEP) genutzt.
Es basiert auf Apache Maven und dem aus den Übungen bereits bekannten oop_maven_template.
Für die Bearbeitung wir NetBeans oder VSCodium empfohlen (Projekt kann direkt geöffnet werden), 
sie können es aber auch in jeder anderen IDE, z.B. Eclipse JDT (importieren!), IntelliJ 
oder Visual Studio Code verwenden. 
Wichtig ist, dass Sie für die Abgaben unbedingt den Buildprozess über __Maven__ starten
und danach prüfen, ob er fehlerfrei beendet und eine (jeweils neue) ZIP-Datei erzeugt wurde.

## Nutzung
Die einfachste Nutzung ist in der Shell gegeben. Führen Sie jeweils vor einer 
Abgabe ein `mvn clean package` aus. Beachten Sie dabei, dass der Build __fehlerfrei__ abgeschlossen
wird und mit einem __BUILD SUCCESS__ endet - ansonsten korrigieren Sie Ihr Projekt!
Nach dem erfolgreichen Build finden Sie im Verzeichnis ./target eine ZIP-Datei 
(z.B. oop_exam-FS26-xxx.zip), welche Sie direkt für die Abgabe im ILIAS verwenden.

## Enhaltene Libraries (Dependencies)
* LogBack over SLF4J - https://logback.qos.ch/ over https://slf4j.org/
* JUnit 6 - https://junit.org/
* AssertJ - https://assertj.github.io/doc/
* EqualsVerifier - https://jqno.nl/equalsverifier/