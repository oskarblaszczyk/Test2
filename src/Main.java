import java.io.IOException;

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
        st.remove("00-000"); // usuwa "00-000"

        System.out.println("po usunieciu");
        for (int i = 0; i < st.size(); i++) {
            System.out.println(st.get(i)); //powinno wypisac dodane kody pocztowe
        }


        StringContainer st1 = new StringContainer("\\d{2}[-]\\d{3}", true);
        st1.add("02-495");  //git
        //st1.add("02-495");//powinno rzucic wyjatkiem DuplicatedElementOnListException
    }
}
