package pruefungsvorbereitung;

/**
 * TEMPLATE 05 – Interface vs. Abstrakte Klasse.
 *
 * Wann was verwenden?
 *
 * INTERFACE ("kann..."):
 *  - Fuer Faehigkeiten / Rollen
 *  - Beispiele: Switchable, Comparable, Iterable, Drawable
 *  - Eine Klasse kann MEHRERE Interfaces implementieren
 *  - Keine Instanz-Attribute, keine Konstruktoren
 *
 * ABSTRAKTE KLASSE ("ist ein..."):
 *  - Fuer is-a Beziehungen + gemeinsame Basisfunktionalitaet
 *  - Beispiele: Animal, Shape, Vehicle
 *  - Eine Klasse kann nur EINE Basisklasse haben (Einfachvererbung)
 *  - Kann Attribute und Konstruktoren haben
 *
 * ----------------------------------------------------------
 * Dieses Template zeigt das Fox-Rabbit-Beispiel aus SW04:
 *   - Interface Actor  (kann agieren)
 *   - Abstract Animal  (ist ein Tier)
 *   - Konkrete Fox/Rabbit/Hunter
 */
public class _05_InterfaceVsAbstract {

    // ======================================================
    // INTERFACE – definiert eine Faehigkeit/Rolle
    // ======================================================
    public interface Actor {
        /**
         * Jeder Actor hat eine act()-Methode (wird in Subklassen implementiert).
         */
        void act();
    }

    // ======================================================
    // ABSTRAKTE KLASSE – gemeinsame Basis
    // ======================================================
    public static abstract class Animal implements Actor {
        protected boolean alive;
        protected int age;

        protected Animal() {
            this.alive = true;
            this.age = 0;
        }

        // Konkrete Methode (vererbt an alle Subklassen)
        public boolean isAlive() {
            return this.alive;
        }

        public void incrementAge() {
            this.age++;
        }

        // ABSTRAKTE METHODE – jede Subklasse MUSS das implementieren
        protected abstract int getMaxAge();

        // Konkrete Implementierung von act() fuer alle Tiere
        @Override
        public void act() {
            if (alive) {
                incrementAge();
                if (age > getMaxAge()) {
                    alive = false;
                    System.out.println("RIP: " + this.getClass().getSimpleName());
                }
            }
        }
    }

    // ======================================================
    // KONKRETE KLASSEN – erben von Animal
    // ======================================================
    public static class Fox extends Animal {
        @Override
        protected int getMaxAge() {
            return 150;
        }
    }

    public static class Rabbit extends Animal {
        @Override
        protected int getMaxAge() {
            return 40;
        }
    }

    // ======================================================
    // AKTEUR OHNE TIER – nutzt nur das Interface!
    // ======================================================
    public static class Hunter implements Actor {
        @Override
        public void act() {
            System.out.println("Hunter schiesst...");
        }
    }

    // ======================================================
    // DEMO: Polymorphie durch das Interface
    // ======================================================
    public static void main(String[] args) {
        // Sammlung von Actor-Objekten – alle koennen act()!
        Actor[] actors = {
            new Fox(),
            new Rabbit(),
            new Hunter()   // auch wenn es kein Animal ist!
        };

        for (Actor actor : actors) {
            actor.act();  // Polymorphie: richtige Methode wird automatisch gewaehlt
        }

        // Typcheck mit instanceof
        for (Actor actor : actors) {
            if (actor instanceof Animal animal) {  // Pattern Matching (ab Java 16)
                System.out.println("Animal (alive=" + animal.isAlive() + ")");
            }
        }
    }
}
