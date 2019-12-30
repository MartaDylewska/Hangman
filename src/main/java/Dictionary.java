import java.util.*;

public class Dictionary {

    private Map<String, String[]> dictionary = new HashMap<>();

    public Dictionary() {

        getDictionary();
    }

    Map getDictionary(){

        dictionary.put("plays", new String[]{"Podaj graczy:", "Enter players"});
        dictionary.put("play1", new String[]{"Gracz 1", "Player 1"});
        dictionary.put("play2", new String[]{"Gracz 2", "Player 2"});

        return dictionary;
    }

/*    static List<Dictionary> generate(String path){

        List<Dictionary> dictionaries = new Vector<>();

        try{

            Scanner sc = new Scanner(new File(path));

            while(sc.hasNext()) {
                String[] elements = sc.next().split(",");
                dictionaries.add(new Dictionary(elements[1],elements[2],elements[3]));
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("Brak pliku.");
        }
        return dictionaries;
    }*/
}
