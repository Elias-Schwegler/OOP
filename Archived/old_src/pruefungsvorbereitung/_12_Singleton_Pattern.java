package pruefungsvorbereitung;

/**
 * 📌 TEMPLATE: Singleton Pattern.
 *
 * Wann nutzen?
 *  - GENAU EINE Instanz pro JVM noetig (z.B. Logger, Config, ConnectionPool)
 *  - VORSICHT: Antipattern wenn ueberbenutzt -&gt; nimmt globalen State!
 *
 * Drei Varianten:
 *   A) Eager Initialization      - einfach, aber Instanz wird IMMER erzeugt
 *   B) Lazy Initialization (DCL) - on-demand, threadsafe via double-checked locking
 *   C) Enum-Singleton            - Bloch's Empfehlung: kuerzeste, sicherste Variante
 *
 * Klausurfrage: "Warum ist der Konstruktor private?"
 *   -> Damit niemand ausserhalb mit `new` eine zweite Instanz erzeugen kann!
 */
public class _12_Singleton_Pattern {

    // ===== A) EAGER (einfach, ggf. Speicher-Verschwendung) =====
    public static final class ConfigEager {
        private static final ConfigEager INSTANCE = new ConfigEager();
        private final java.util.Properties props = new java.util.Properties();

        private ConfigEager() {
            // ggf. Properties laden
        }

        public static ConfigEager getInstance() { return INSTANCE; }

        public String get(final String key) { return props.getProperty(key); }
    }

    // ===== B) LAZY mit Double-Checked Locking (threadsafe) =====
    public static final class ConfigLazy {
        // volatile! sonst koennte Thread eine "halbinitialisierte" Instanz sehen
        private static volatile ConfigLazy instance;
        private ConfigLazy() {}

        public static ConfigLazy getInstance() {
            if (instance == null) {                 // 1. Check (ohne Lock = schnell)
                synchronized (ConfigLazy.class) {
                    if (instance == null) {         // 2. Check (mit Lock = korrekt)
                        instance = new ConfigLazy();
                    }
                }
            }
            return instance;
        }
    }

    // ===== C) ENUM-Singleton (Bloch: "the best way") =====
    public enum Logger {
        INSTANCE;

        public void log(final String msg) {
            System.out.println("[LOG] " + msg);
        }
        // Vorteile: serialisierungs-sicher, reflection-sicher, threadsafe by JVM
    }

    public static void main(final String[] args) {
        Logger.INSTANCE.log("Hallo Singleton");
        ConfigLazy.getInstance(); // initialisiert beim ersten Zugriff
    }
}
