import java.io.File;
import java.io.InputStream;
import java.util.*;

public class Dictionary extends HashMap<String, String[]>{

    private static Dictionary dictionary;

    private Dictionary() {
        generate();
    }

    public static Dictionary getInstance(){
        if(dictionary == null)
            dictionary = new Dictionary();
        return dictionary;
    }

    private void generate(){

        this.put("plays", new String[]{"Podaj graczy:", "Enter players:"});
        this.put("play1", new String[]{"Gracz 1", "Player 1"});
        this.put("play2", new String[]{"Gracz 2", "Player 2"});
        this.put("reset", new String[]{"Wyczyść", "Reset"});
        this.put("message1", new String[]{"Uzupełnij dane.", "Complete datas."});
        this.put("message2", new String[]{"Nazwy graczy nie mogą być takie same.", "Players' names cannot be the same."});
        this.put("guess", new String[]{" zgaduje.", " is guessing."});
        this.put("invent", new String[]{" wymyśla hasło.", " invents a word."});
        this.put("category", new String[]{"Kategoria: ", "Category: "});
        this.put("categories0", new String[]{"--wybierz--", "--choose--"});
        this.put("categories1", new String[]{"gra", "game"});
        this.put("categories2", new String[]{"hobby", "hobby"});
        this.put("categories3", new String[]{"imię", "name"});
        this.put("categories4", new String[]{"mebel", "furniture"});
        this.put("categories5", new String[]{"miasto", "city"});
        this.put("categories6", new String[]{"muzyka", "music"});
        this.put("categories7", new String[]{"państwo", "country"});
        this.put("categories8", new String[]{"pierwiastek", "element"});
        this.put("categories9", new String[]{"pojazd", "vehicle"});
        this.put("categories10", new String[]{"potrawy", "food"});
        this.put("categories11", new String[]{"przedmiot", "article"});
        this.put("categories12", new String[]{"roślina", "plant"});
        this.put("categories13", new String[]{"rzeka", "river"});
        this.put("categories14", new String[]{"sport", "sport"});
        this.put("categories15", new String[]{"zwierzę", "animal"});
        this.put("categories16", new String[]{"inne", "other"});
        this.put("categoryShow", new String[]{"Pokaż kategorię", "Show category"});
        this.put("word", new String[]{"Hasło", "Word"});
        this.put("tip", new String[]{"Tylko małe litery polskiego alfabetu (wyłącznie pierwsza litera może być wielka)",
        "Only small letters allowed (just the first one can be capitalized)."});
        this.put("wordPattern", new String[]{"[A-ZĆŁÓŚŻŹa-zćłóśżź][a-ząćęłńóśżź]+", "[A-Za-z][a-z]+"});
        this.put("message3", new String[]{"Niedozwolone hasło.", "Word not allowed."});
        this.put("message4", new String[]{"Zbyt długie hasło. \nMax 32 litery.", "Word too long. \nMax 32 letters."});
        this.put("word1", new String[]{"Hasło: ", "Word: "});
        this.put("newGame", new String[]{"Nowa gra", "New game"});
        this.put("play", new String[]{"Dalej", "Continue"});
        this.put("end", new String[]{"Koniec", "The end"});

    }

    /*   private void generate(){

        try{
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("dict.txt");
            File file = new File(getClass().getClassLoader().getResource("dict.txt").getFile());

            Scanner sc = new Scanner(file);

            while(sc.hasNext()) {
                String[] elements = sc.next().split(",");
                this.put(elements[1], new String[]{elements[2],elements[3]});
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("Brak pliku.");
        }
    } */
}
