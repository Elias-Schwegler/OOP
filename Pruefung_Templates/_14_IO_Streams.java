
/**
 * 📌 TEMPLATE: IO-Datenstroeme (SW12 / O13).
 *
 * Faustregeln:
 *   - try-with-resources IMMER (Stream wird automatisch geschlossen)
 *   - BufferedXxx um FileXxx wickeln (Performance: 10-100x schneller)
 *   - DataXxx fuer primitive Typen (int/float/double in fester Byte-Anzahl)
 *   - ObjectXxx fuer komplette Java-Objekte (Serialisierung) -&gt; Klasse muss Serializable sein
 *   - PrintWriter/Reader fuer Text + Encoding (UTF-8 explizit angeben!)
 *
 * Stream-Hierarchie (Kurzform):
 *   - Byte-Streams:      InputStream  / OutputStream         (binary, 8-bit)
 *   - Character-Streams: Reader       / Writer               (text, 16-bit Unicode)
 *
 * Dekorator-Pattern in Aktion:
 *   new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))
 *   |    Buffer           |  Bytes -&gt; Chars         |  Datei lesen
 */
public class _14_IO_Streams {

    public static void main(final String[] args) throws java.io.IOException {
        final java.nio.file.Path tmp = java.nio.file.Files.createTempFile("io-template-", ".txt");
        try {
            schreibeText(tmp.toString(), java.util.List.of("Hallo", "Welt", "OOP"));
            for (final String line : leseText(tmp.toString())) System.out.println(line);

            final java.nio.file.Path bin = java.nio.file.Path.of(tmp + ".bin");
            schreibeBinary(bin.toString(), new int[]{42, 1337, -1});
            System.out.println(java.util.Arrays.toString(leseBinary(bin.toString())));
            java.nio.file.Files.deleteIfExists(bin);
        } finally {
            java.nio.file.Files.deleteIfExists(tmp);
        }
    }

    // ===== TEXT (Reader/Writer) =====
    public static void schreibeText(final String path, final java.util.List<String> lines) throws java.io.IOException {
        try (java.io.BufferedWriter bw = java.nio.file.Files.newBufferedWriter(
                java.nio.file.Path.of(path), java.nio.charset.StandardCharsets.UTF_8)) {
            for (final String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        }
    }

    public static java.util.List<String> leseText(final String path) throws java.io.IOException {
        return java.nio.file.Files.readAllLines(java.nio.file.Path.of(path),
                java.nio.charset.StandardCharsets.UTF_8);
    }

    // ===== BINARY (DataInput/Output) =====
    public static void schreibeBinary(final String path, final int[] werte) throws java.io.IOException {
        try (java.io.DataOutputStream dos = new java.io.DataOutputStream(
                new java.io.BufferedOutputStream(new java.io.FileOutputStream(path)))) {
            dos.writeInt(werte.length);
            for (final int v : werte) dos.writeInt(v);
        }
    }

    public static int[] leseBinary(final String path) throws java.io.IOException {
        try (java.io.DataInputStream dis = new java.io.DataInputStream(
                new java.io.BufferedInputStream(new java.io.FileInputStream(path)))) {
            final int n = dis.readInt();
            final int[] result = new int[n];
            for (int i = 0; i < n; i++) result[i] = dis.readInt();
            return result;
        }
    }

    // ===== KOPIEREN (Streams pipen) =====
    public static void kopiere(final String src, final String dst) throws java.io.IOException {
        try (java.io.InputStream  is = new java.io.FileInputStream(src);
             java.io.OutputStream os = new java.io.FileOutputStream(dst)) {
            is.transferTo(os);   // Java 9+: schiebt alle Bytes von is nach os
        }
    }
}
