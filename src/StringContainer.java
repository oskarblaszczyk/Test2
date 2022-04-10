/*
Zadanie01: napisz kod tak aby fragment ponizej dzialal i sie kompilowal. - ale nie mozesz uzywac tablic, list, setow, zadnych kolekcji czy kolejek, ani konkatenowac to w Stringi czy appendowac
        w string buildery

//tworzymy klaske String container ktora bedzie mogla przyjmowac tylko Stringi z okreslonym Patternem podanym jako argument.
//podczas tworzenia obiektu nalezy zdefinowac poprawnosc patternu i jesli pattern bedzie "zly- czyli taki ktory sie nie kompiluje" to ma zostac rzucony wyjatek InvalidStringContainerPatternException(badPattern)
//wszystkie wyjatki w programie maja dziedziczyc RuntimeException.
//tu w przykladzie dodajemy kody pocztowe
        StringContainer st = new StringContainer("\\d{2}[-]\\d{3}");

        st.add("02-495");//git
        st.add("01-120");//git
        st.add("05-123");//git
        st.add("00-000");//git
        //st.add("ala ma kota"); //powinno sie wywalic wyjatkiem InvalidStringContainerValueException(badValue)
        for(int i=0; i<st.size(); i++){
        System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }

        st.remove(0);  //usuwa "02-495"
        st.remove("00-000"); // usuwa "00-000"

        System.out.println("po usunieciu");
        for(int i=0; i<st.size(); i++){
        System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }

        nasza liste mozna tez parametryzowac tak aby nie dalo sie wrzucac powtorzen np:
        StringContainer st = new StringContainer("\\d{2}[-]\\d{3}", true); //jakis parametr np: duplicatedNotAllowed - domyslnie false
        wtedy np:
        st.add("02-495");//git
        st.add("02-495");//powinno rzucic wyjatkiem DuplicatedElementOnListException
 */


import java.io.*;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringContainer {
    private String pattern;
    private int size = 0;
    private int objectIndex = 0;
    private boolean duplicatedNotAllowed = false;
    File file = new File("file");
    File file2 = new File("file2");
    File tempFile = new File("file-r");

    // todo parametryzacja nazwy pliku!!!!!
    public StringContainer(String pattern) throws IOException {
        try {
            Pattern.compile(pattern);
        } catch (Exception exception) {
            throw new RuntimeException("InvalidStringContainerPatternException(badPattern)");
        }
        this.pattern = pattern;
        createNewFile();
        objectIndex++;
    }

    public StringContainer(String pattern, boolean duplicatedNotAllowed) throws IOException {
        try {
            Pattern.compile(pattern);
        } catch (Exception exception) {
            throw new RuntimeException("InvalidStringContainerPatternException(badPattern)");
        }
        this.pattern = pattern;
        this.duplicatedNotAllowed = duplicatedNotAllowed;
        createNewFile();
        objectIndex++;

    }

    private void createNewFile() throws IOException {
        FileWriter fw = new FileWriter(file2);
        fw.write("");
        fw.close();
    }

    public void add(String s) throws IOException {
        FileWriter fw = new FileWriter(file2, true);
        fw.write(s + "\n");
        fw.close();
        size++;
    }

    public String get(int i) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file2));
        String str = "";
        int line = 0;
        while ((str = br.readLine()) != null) {
            if (line == i) {
                return str;
            }
            line++;
        }
        br.close();
        return null;
    }

    public void remove(int i) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(file2));
//        FileWriter fw = new FileWriter(tempFile);
//        String str = "";
//        int line = 0;
//        while ((str = br.readLine()) != null) {
//            if (line == i) {
//                size--;
//            } else {
//                fw.write(str + "\n");
//            }
//            line++;
//
//        }
//        fw.close();
//        br.close();

        file2.delete();
        Files.delete(file2.toPath());
        tempFile.renameTo(file2);

    }
//    public void add(String s) throws IOException {
//
//        String str = Files.readString(file.toPath());
//        FileWriter fw = new FileWriter("file", true);
//        fw.write(createString(Integer.toString(size), s));
//        fw.close();
//        size++;
//    }

//     W metodzie ponizej uparlem sie aby dodawac '[' na poczatku i ']' na koncu jednak wyglada to slabo ale dziala :D

//    public void add(String s) throws IOException {
//        int index = 0;
//        String str = readFile("file");
//
//        FileWriter fw = new FileWriter("file");
//        fw.write("[");
//
//        if (str != null) {
//            Pattern p = Pattern.compile(createString("\\d*", pattern));
//            Matcher m = p.matcher(str);
//
//            while (m.find()) {
//                fw.write(str.substring(m.start(), m.end()));
//                index++;
//            }
//        }
//
//        fw.write(createString(Integer.toString(index), s));
//        fw.write("]");
//        fw.close();
//        index++;
//        size = index;
//    }
//    //todo konkatenuje tutaj w stringa, a nie mozna.
//    private String createString(String index, String s) {
//        return "{\"index\":" + index + ",\"String\":\"" + s + "\"}";
//    }
//
//    public String get(int i) throws IOException {
//        String str = Files.readString(file.toPath());
//        Pattern p = Pattern.compile("\\" + (createString(Integer.toString(i), pattern))); //todo sprawdzic \\ czemu createString
//        Matcher m = p.matcher(str);
//        if (m.find()) {
//            return str.substring(m.end() - 8, m.end() - 2);
//        }
//        return null;
//    }
//     public void print() throws IOException {
//         System.out.println(Files.readString(file.toPath()));
//     }

//    public void remove(int i) throws IOException {
//        int count = 0;
//        String str = Files.readString(file.toPath());
//        FileWriter fw = new FileWriter(tempFile);
//        for (int j = 0; j < size; j++) {
//            if (j == i) {
//                continue;
//            }
//            fw.write(createString(Integer.toString(count), get(j)));
//            count++;
//        }
//        fw.close();
//        file.delete();
//        tempFile.renameTo(file);
//
//        size = count;
//    }


//    public String get(String s) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("file"));
//        String str = br.readLine();
//        while (p1.matcher(str).find()) {
//            if (s.equals(str.substring(p1.matcher(str).start(), p1.matcher(str).end()))) {
//                return str.substring(p1.matcher(str).start(), p1.matcher(str).end());
//            }
//        }
//        return null;
//    }

    public int size() {
        return size;
    }

//    private String readFile(String file) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(file));
//        String str = br.readLine();
//        return str;
//    }


//    public String getPattern() {
//        return pattern;
//    }

    @Override
    public String toString() {
        return "StringContainer{" +
                "pattern='" + '\'' +
                '}';
    }
}
