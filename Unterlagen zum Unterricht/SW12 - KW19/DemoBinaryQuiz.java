/*
 * Copyright 2026 Hochschule Luzern Informatik.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.hslu.oop.oop13;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

/**
 * Schreibt eine int-Zahl in eine Datei.
 * Aber was passiert da genau? Was sehen Sie in der Datei?
 */
public final class DemoBinaryQuiz {

    private static final Logger LOG = LoggerFactory.getLogger(DemoBinaryQuiz.class);
    private static final String TXT_FILE = "demo.txt";

    /**
     * main-Methode für Demo.
     *
     * @param args nicht verwendet.
     */
    public static void main(final String[] args) {
        final int value = 825_373_492;
        try ( DataOutputStream dos = new DataOutputStream(new FileOutputStream(TXT_FILE))) {
            dos.writeInt(value);
            LOG.info("Zahl '{}' (int) in Datei '{}' geschrieben.", value, TXT_FILE);
        } catch (IOException exception) {
            LOG.error(exception.getMessage(), exception);
        }
    }
}
