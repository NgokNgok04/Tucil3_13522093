package src.backend.util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

public class Dictionary {
    private HashSet<String> dictionary;

    public Dictionary() {
        this.dictionary = new HashSet<>();
        loadDictionary("src/backend/util/Dictionary.txt");
    }

    public void loadDictionary(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isAValidWord(String word) {
        return dictionary.contains(word);
    }

    public static List<String> findAllPossibleWord(String word,Map<String,Boolean> visitedWord) {
        word = word.toLowerCase();

        List<String> tempPossibleWord = new ArrayList<>();
        Dictionary dictionary = new Dictionary();
        
        String resetWord = new String(word);

        for (int i = 0; i < word.length(); i++){
            word = new String(resetWord);
            
            for (int j = 0; j < 26; j++){
                // charArray[i] = (char) (97 + j); //97 = 'a'
                String modifiedString;
                if (i != 0){
                    modifiedString = word.substring(0,i) + ((char) (97 + j)) + word.substring(i + 1);
                } else {
                    modifiedString = ((char) (97 + j)) + word.substring(1);
                }

                if (!modifiedString.equals(resetWord) && visitedWord.get(modifiedString) == null){
                    if(dictionary.isAValidWord(modifiedString)){
                        tempPossibleWord.add(modifiedString);
                    }
                }
            }
        }

        return tempPossibleWord;
    }

}
