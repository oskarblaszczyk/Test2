import exceptions.DuplicatedElementOnListException;
import exceptions.InvalidStringContainerPatternException;
import exceptions.InvalidStringContainerValueException;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class StringContainer {
    private final String pattern;
    private final boolean duplicatedNotAllowed;
    private int size = 0;
    private static int objectCounter = 0;
    private final File file = new File("SC_" + objectCounter);
    private final File tempFile = new File(".temp_" + file);

    /**
     * Konstruuje obiekt przechowujacy Stringi zgodne z podanym patternem.
     * Dozwolone są duplikaty.
     */
    public StringContainer(String pattern) throws IOException {
        verifyPattern(pattern);
        this.pattern = pattern;
        duplicatedNotAllowed = false;
        objectCounter++;
        createNewFile();
    }

    /**
     * Konstruuje obiekt przechowujacy Stringi zgodne z podanym patternem.
     * Duplikaty moga byc niedozwolone.
     */
    public StringContainer(String pattern, boolean duplicatedNotAllowed) throws IOException {
        verifyPattern(pattern);
        this.pattern = pattern;
        this.duplicatedNotAllowed = duplicatedNotAllowed;
        objectCounter++;
        createNewFile();
    }

    /**
     * Dodaje String zgodny z pattern do konca pliku (listy)
     */
    public void add(String s) throws IOException {
        if (duplicatedNotAllowed && indexOf(s) >= 0) {
            throw new DuplicatedElementOnListException();
        }
        verifyString(s);
        FileWriter fw = new FileWriter(file, true);
        fw.write(s + "\n");
        fw.close();
        size++;
    }

    /**
     * Zwraca String o podanym indeksie
     */
    public String get(int i) throws IOException {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        int line = 0;
        while ((str = br.readLine()) != null) {
            if (line == i) {
                br.close(); // pol dnia szukalem czemu mi nie chce pliku skasowac :D
                return str;
            }
            line++;
        }
        br.close();
        return null;
    }

    /**
     * Kasuje String o podanym indeksie
     */
    public void remove(int i) throws IOException {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException();
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        FileWriter fw = new FileWriter(tempFile);
        String str;
        int line = 0;
        while ((str = br.readLine()) != null) {
            if (line == i) {
                size--;
            } else {
                fw.write(str + "\n");
            }
            line++;
        }
        br.close();
        fw.close();
        file.delete();
        tempFile.renameTo(file);
    }

    /**
     * Kasuje pierwszego znalezionego stringa z listy
     */
    public void remove(String s) throws IOException {
        if (indexOf(s) < 0) {
            throw new NoSuchElementException();
        }
        remove(indexOf(s));
    }

    /**
     * Zwraca index pierwszego znalezionego String'a.
     * Jezeli nie istnieje to zwraca -1
     */
    private int indexOf(String s) throws IOException {
        verifyString(s);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        int index = 0;
        while ((str = br.readLine()) != null) {
            if (str.equals(s)) {
                br.close();
                return index;
            }
            index++;
        }
        br.close();
        return -1;
    }

    /**
     * Tworzy nowy pusty plik lub nadpisuje istniejacy.
     */
    private void createNewFile() throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write("");
        fw.close();
    }

    /**
     * Weryfikuje zgodnosc podanego String z Patternem
     */
    private void verifyString(String s) {
        if (!Pattern.matches(pattern, s)) {
            throw new InvalidStringContainerValueException("badValue");
        }
    }

    /**
     * Weryfikuje czy pattern się kompiluje
     */
    private void verifyPattern(String s) {
        try {
            Pattern.compile(s);
        } catch (Exception exception) {
            throw new InvalidStringContainerPatternException("badPattern");
        }
    }

    /**
     * Zwraca ilosc dodanych elementow
     */
    public int size() {
        return size;
    }

    public String getPattern() {
        return pattern;
    }

    public boolean isDuplicatedNotAllowed() {
        return duplicatedNotAllowed;
    }

    @Override
    public String toString() {
        return "StringContainer numer:" +
                objectCounter +
                " pattern: '" +
                pattern;

    }
}
