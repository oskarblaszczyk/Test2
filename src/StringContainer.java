import exceptions.*;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.regex.Pattern;

public class StringContainer {
    private final String pattern;
    private final boolean duplicatedNotAllowed;
    private int size = 0;
    private StringNode firstNode;

    public StringContainer(String pattern) {
        verifyPattern(pattern);
        this.pattern = pattern;
        duplicatedNotAllowed = false;
        firstNode = null;
    }

    public StringContainer(String pattern, boolean duplicatedNotAllowed) {
        verifyPattern(pattern);
        this.pattern = pattern;
        this.duplicatedNotAllowed = duplicatedNotAllowed;
    }

    /**
     * Dodaje String na koncu listy.
     *
     * @param str zgodny z pattern.
     */
    public void add(String str) {
        verifyString(str);
        if (duplicatedNotAllowed && isDuplicated(str)) {
            throw new DuplicatedElementOnListException(str);
        }

        if (firstNode == null) {
            firstNode = new StringNode(str);
        } else {
            StringNode node = firstNode;
            while (node.nextNode != null) {
                node = node.nextNode;
            }
            node.nextNode = new StringNode(str);
        }
        size++;
    }

    /**
     * Zwraca String z obiektu listy o podanym indeksie.
     * jezeli podany index poza zakresem -> wyjatek
     *
     * @param index indeks obiektu do pobrania String
     * @return wartosc String
     */
    public String get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        return getNode(index).name;
    }

    /**
     * Kasuje String o podanym indeksie.
     * Jezeli indeks poza zakresem -> wyjatek
     *
     * @param index kasowany String
     */
    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException(index);
        }
        removeNode(getNode(index));
    }

    /**
     * Kasuje pierwszy zgodny String
     * Jezeli brak podanego na liscie -> wyjatek
     *
     * @param str kasowany String zgodny z pattern
     */
    public void remove(String str) {
        verifyString(str);
        removeNode(getNode(str));
    }

    /**
     * Zwraca ilosc elementow w Liscie
     */
    public int size() {
        return size;

    }

    //lepiej zostawic void czy zwracac boolean?

    /**
     * Kasuje pierwszy zgodny String(obiekt listy)
     *
     * @param node obiekt do skasowania
     */
    private void removeNode(StringNode node) {
        StringNode first = firstNode;
        if (node.equals(first)) {
            firstNode = first.nextNode;
            size--;
        } else {
            while (first.nextNode != null) {
                if (node.equals(first.nextNode)) {
                    first.nextNode = first.nextNode.nextNode;
                    size--;
                    break;
                }
                first = first.nextNode;
            }
        }
    }

    /**
     * Zwraca pierwszy znaleziony obiekt String
     * Jezeli nie znaleziono rzuca wyjatek.
     *
     * @param str String zgodny z pattern
     * @return obiekt String
     */
    private StringNode getNode(String str) {
        StringNode node = firstNode;
        while (node != null) {
            if (node.name.equals(str)) {
                return node;
            }
            node = node.nextNode;
        }
        throw new NoSuchElementException(str);
    }

    /**
     * Zwraca obiekt String o podanym indeksie
     *
     * @param index indeks Obiektu String
     * @return obiekt String
     */
    private StringNode getNode(int index) {
        StringNode node = firstNode;
        if (index != 0) {
            for (int i = 1; i <= index; i++) {
                node = node.nextNode;
            }
        }
        return node;
    }

    /**
     * Weryfikuje zgodnosc podanego String z Patternem
     */
    private void verifyString(String s) {
        if (!Pattern.matches(pattern, s)) {
            throw new InvalidStringContainerValueException(s);
        }
    }

    /**
     * Weryfikuje czy pattern się kompiluje
     */
    private void verifyPattern(String s) {
        try {
            Pattern.compile(s);
        } catch (Exception exception) {
            throw new InvalidStringContainerPatternException(s);
        }
    }

    /**
     * Sprawdza czy podany String wystepuje w jakimkolwiek elemencie
     *
     * @param s String do sprawdzenia
     * @return true jesli wystepuje
     */
    private boolean isDuplicated(String s) {
        StringNode node = firstNode;
        while (node != null) {
            if (node.name.equals(s)) {
                return true;
            }
            node = node.nextNode;
        }
        return false;
    }

    //te dwa gettery nie są potrzebne dla tej tresci zadania ale akurat one moga sie przydac.
    public String getPattern() {
        return pattern;
    }

    public boolean isDuplicatedNotAllowed() {
        return duplicatedNotAllowed;
    }

    /**
     * Klasa wewnetrzna definijuca elementy.
     * Kazdy element posiada wskazany kolejny.
     */
    private static class StringNode {
        private final String name;
        private StringNode nextNode;

        public StringNode(String name) {
            this.name = name;
            this.nextNode = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StringNode that = (StringNode) o;
            return Objects.equals(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}