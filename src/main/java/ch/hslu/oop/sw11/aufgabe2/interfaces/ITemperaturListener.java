package ch.hslu.oop.sw11.aufgabe2.interfaces;

import java.util.EventListener;

import ch.hslu.oop.sw11.aufgabe2.events.TemperaturEvent;

/**
 * SW11 / U10 Aufgabe 2: Eigenes Listener-Interface.
 *
 * Naming-Konvention:
 *  - Praefix "I" fuer Interfaces (eigentlich C#-Stil; Java-Standard waere "TemperaturListener"
 *    ohne Praefix). Auf Wunsch des Studenten konsistent in diesem Projekt verwendet.
 *
 * Konvention (Java-Beans):
 *  - extends EventListener (Marker-Interface fuer Java-Beans-Tools)
 *  - @FunctionalInterface -> Lambda-faehig
 *  - genau EINE Methode mit Endung "ed"
 */
@FunctionalInterface
public interface ITemperaturListener extends EventListener {
    void temperaturChanged(TemperaturEvent event);
}
