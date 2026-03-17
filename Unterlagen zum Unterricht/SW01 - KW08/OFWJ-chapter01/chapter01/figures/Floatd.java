
/**
 * Write a description of class Floatd here.
 *float 7 Stellen genau, dann nicht mehr optimal
 *double 14 Stellen genau, "
 */
public class Floatd
{
    public void dangerousFloatLoop() {
        System.out.println("--- DANGEROUS FLOAT LOOP ---");
        double value = 0.9f;
        int iterations = 0;

        while (value < 1.0f && iterations < 10000) {
            value += 0.000025f;
            iterations++;
        }

        // Erwartet: (1.0 - 0.9) / 0.000025 = 0.1 / 0.000025 = 4000 Iterationen
        System.out.println("Iterationen (erwartet 4000): " + iterations);
        System.out.println("Endwert: " + value);
    }
    
        public void safeForLoop() {
        System.out.println("--- SAFE FOR LOOP ---");
        double value = 0.9f;
        for (int i = 0; i < 4000; i++) {
            value += 0.000025f;
        }
        System.out.println("Wert nach 4000 Iterationen (Sollte 1.0f sein): " + value);
        // Ergebnis ist meistens etwas wie 1.0000001
    }
}