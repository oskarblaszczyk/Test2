import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {

        StringContainer st = new StringContainer("\\d{2}[-]\\d{3}");
        st.add("02-495");//git
        st.add("01-120");//git
        st.add("05-123");//git
        st.add("00-000");//git

        //st.add("ala ma kota"); //powinno sie wywalic wyjatkiem InvalidStringContainerValueException(badValue)
        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }

        st.remove(0);  //usuwa "02-495"
//        st.remove("00-000"); // usuwa "00-000"

       System.out.println("po usunieciu");
        for(int i=0; i<st.size(); i++){
            System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }
    }
}
