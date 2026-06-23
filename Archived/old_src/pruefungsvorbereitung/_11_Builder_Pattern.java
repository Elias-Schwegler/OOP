package pruefungsvorbereitung;

/**
 * 📌 TEMPLATE: Builder Pattern.
 *
 * Wann nutzen?
 *  - Konstruktor mit zu vielen (5+) Parametern
 *  - Manche Parameter optional
 *  - "Telescoping Constructor"-Antipattern vermeiden
 *
 * Beispiel: HTTP-Request, Pizza, Datenbank-Query...
 *
 * REZEPT:
 *   1. Klasse mit private Konstruktor (nur Builder darf erzeugen)
 *   2. Statische innere Builder-Klasse mit Setter-Methoden, die `this` zurueckgeben
 *   3. Builder.build() erzeugt das Zielobjekt
 */
public class _11_Builder_Pattern {

    // ====== Zielobjekt (immutable!) ======
    public static final class HttpRequest {
        private final String url;          // pflicht
        private final String method;       // pflicht
        private final String body;         // optional
        private final int timeoutSeconds;  // optional
        private final java.util.Map<String, String> headers;

        // Privater Konstruktor: nur via Builder erreichbar
        private HttpRequest(final Builder b) {
            this.url            = java.util.Objects.requireNonNull(b.url,    "url");
            this.method         = java.util.Objects.requireNonNull(b.method, "method");
            this.body           = b.body;
            this.timeoutSeconds = b.timeoutSeconds;
            this.headers        = java.util.Map.copyOf(b.headers);
        }

        public String getUrl()        { return url; }
        public String getMethod()     { return method; }
        public String getBody()       { return body; }
        public int    getTimeoutSec() { return timeoutSeconds; }
        public java.util.Map<String, String> getHeaders() { return headers; }

        @Override
        public String toString() {
            return method + " " + url + " (timeout=" + timeoutSeconds + "s, headers=" + headers + ")";
        }

        // ====== Statischer Einstiegspunkt ======
        public static Builder builder(final String url) {
            return new Builder(url);
        }

        // ====== Builder ======
        public static final class Builder {
            private final String url;
            private String method = "GET";
            private String body = null;
            private int timeoutSeconds = 30;
            private final java.util.Map<String, String> headers = new java.util.HashMap<>();

            private Builder(final String url) { this.url = url; }

            public Builder method(final String method)       { this.method = method;            return this; }
            public Builder body(final String body)           { this.body = body;                return this; }
            public Builder timeoutSeconds(final int seconds) { this.timeoutSeconds = seconds;   return this; }
            public Builder header(final String k, final String v) { headers.put(k, v);          return this; }

            public HttpRequest build() { return new HttpRequest(this); }
        }
    }

    public static void main(final String[] args) {
        final HttpRequest req = HttpRequest.builder("https://hslu.ch/api/grades")
                .method("POST")
                .header("Authorization", "Bearer xyz")
                .header("Accept", "application/json")
                .body("{\"semester\":\"FS26\"}")
                .timeoutSeconds(10)
                .build();
        System.out.println(req);
    }
}
