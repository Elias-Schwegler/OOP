
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * TEMPLATE 21 - GUI + MVC (Woche 14 / SW14).
 *
 * WICHTIG zur STOFFABGRENZUNG:
 *  - Der KURS nutzte JavaFX (Kapitel 13). Hier verwenden wir SWING (javax.swing),
 *    weil Swing im JDK steckt und somit mit reinem "javac" kompiliert
 *    (JavaFX ist NICHT im Standard-Classpath).
 *  - Das KONZEPT ist identisch: MVC, Event-Handler (Listener), Widgets
 *    (Label, Button, Panel). Wer Swing versteht, versteht JavaFX analog.
 *  - Pruefungsziel: GUI-Code LESEN und AENDERN koennen, nicht auswendig tippen.
 *
 * ----------------------------------------------------------
 * MVC = Model-View-Controller. WARUM diese Trennung?
 *  - MODEL: die reinen Daten + Geschaeftslogik. Weiss NICHTS von der GUI.
 *  - VIEW : die Darstellung (Fenster, Buttons, Labels). Weiss NICHTS von Logik.
 *  - CONTROLLER: das Bindeglied. Reagiert auf View-Events, ruft Model auf.
 *
 * Vorteil: Model ist ohne GUI testbar, View austauschbar (Swing -> JavaFX),
 * Aenderungen bleiben lokal. Lose Kopplung statt "alles in einer Klasse".
 *
 * Datenfluss (Klick auf "+1"):
 *   View-Button -> Controller -> model.increment()
 *   -> Model meldet via Observer-Callback -> Controller aktualisiert View.
 */
public class _21_GUI_MVC {

    // ======================================================
    // MODEL - nur Daten + Logik, KEINE Swing-Imports!
    // ======================================================
    public static class CounterModel {
        private int value;

        // Observer-Liste: wer will benachrichtigt werden, wenn sich value aendert?
        // Consumer<Integer> = Callback, der den neuen Wert bekommt (Lambda-faehig).
        private final List<Consumer<Integer>> observers = new ArrayList<>();

        public void addObserver(final Consumer<Integer> observer) {
            observers.add(observer);
        }

        public int getValue() {
            return value;
        }

        public void increment() {
            value++;
            notifyObservers();   // WHY: Model "pusht" Aenderung an alle Interessierten
        }

        public void decrement() {
            value--;
            notifyObservers();
        }

        public void reset() {
            value = 0;
            notifyObservers();
        }

        // Ruft jeden registrierten Callback mit dem neuen Wert auf.
        private void notifyObservers() {
            for (final Consumer<Integer> observer : observers) {
                observer.accept(value);
            }
        }
    }

    // ======================================================
    // VIEW - nur Darstellung. Kennt KEIN Model.
    // Bietet Buttons nach aussen an, damit der Controller Listener anhaengen kann.
    // ======================================================
    public static class CounterView extends JFrame {
        private final JLabel labelValue = new JLabel("0", JLabel.CENTER);
        private final JButton buttonPlus = new JButton("+1");
        private final JButton buttonMinus = new JButton("-1");
        private final JButton buttonReset = new JButton("Reset");

        public CounterView() {
            super("Zaehler (MVC-Demo)");   // Fenstertitel

            labelValue.setFont(new Font("SansSerif", Font.BOLD, 40));

            // Buttons unten nebeneinander in ein Panel (FlowLayout per default).
            final JPanel buttonPanel = new JPanel();
            buttonPanel.add(buttonMinus);
            buttonPanel.add(buttonReset);
            buttonPanel.add(buttonPlus);

            // BorderLayout: grosse Zahl in die Mitte, Buttons nach unten.
            setLayout(new BorderLayout());
            add(labelValue, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);

            setDefaultCloseOperation(EXIT_ON_CLOSE); // Fenster zu -> Programm endet
            setSize(300, 200);
            setLocationRelativeTo(null);             // Fenster zentrieren
        }

        // View-Methode: aktualisiert nur die Anzeige (wird vom Controller genutzt).
        public void showValue(final int value) {
            labelValue.setText(Integer.toString(value));
        }

        // Getter, damit der Controller die Listener registrieren kann.
        public JButton getButtonPlus()  { return buttonPlus; }
        public JButton getButtonMinus() { return buttonMinus; }
        public JButton getButtonReset() { return buttonReset; }
    }

    // ======================================================
    // CONTROLLER - das Bindeglied: View-Events -> Model-Aufrufe.
    // ======================================================
    public static class CounterController {
        private final CounterModel model;
        private final CounterView view;

        public CounterController(final CounterModel model, final CounterView view) {
            this.model = model;
            this.view = view;

            // 1) View-Events mit Lambdas an Model-Methoden binden.
            //    addActionListener(e -> ...): e ist das ActionEvent (hier ignoriert).
            view.getButtonPlus().addActionListener(e -> model.increment());
            view.getButtonMinus().addActionListener(e -> model.decrement());
            view.getButtonReset().addActionListener(e -> model.reset());

            // 2) Auf Model-Aenderungen hoeren und View nachziehen (Observer).
            //    view::showValue ist eine Methodenreferenz = Consumer<Integer>.
            model.addObserver(view::showValue);

            // 3) Startzustand einmalig anzeigen.
            view.showValue(model.getValue());
        }
    }

    // ======================================================
    // DEMO / main - startet das Fenster.
    // ======================================================
    public static void main(final String[] args) {
        // WHY invokeLater: Swing ist NICHT thread-safe. GUI-Aufbau und Updates
        // muessen im "Event Dispatch Thread" (EDT) passieren, nicht im main-Thread.
        SwingUtilities.invokeLater(() -> {
            final CounterModel model = new CounterModel();
            final CounterView view = new CounterView();
            new CounterController(model, view);   // verdrahtet Model <-> View
            view.setVisible(true);                // Fenster anzeigen
        });
    }
}
