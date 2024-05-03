package src.backend.util;

import java.util.ArrayList;
import java.util.List;

public class Pair {
    private List<String> sequenceWord;
    private int value;

    public Pair(){
        this.value = 100;
        this.sequenceWord = new ArrayList<String>();
    }
    
    public Pair(List<String> sequenceWord, int value){
        this.sequenceWord = sequenceWord;
        this.value = value;
    }

    public List<String> getSequenceWord() {
        return this.sequenceWord;
    }

    public String getWord() {
        return this.sequenceWord.get(this.sequenceWord.size() - 1);
    }

    public int getValue() {
        return this.value;
    }

    public boolean isEqual(Pair pairToCompare){
        return pairToCompare.value == this.value && pairToCompare.getSequenceWord().equals(this.getSequenceWord());
    }

    public void displayPair(){
        System.out.print("([");
        for(int i = 0; i < this.sequenceWord.size(); i++){
            System.out.print(this.sequenceWord.get(i));
            if (i != this.sequenceWord.size() - 1){
                System.out.print(",");
            }

        }
        System.out.print("],");
        System.out.print(this.getValue());
        System.out.print(")");
    }
}
