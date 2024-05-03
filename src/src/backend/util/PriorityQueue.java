package src.backend.util;
import src.backend.util.Pair;

import java.util.ArrayList;
import java.util.List;
public class PriorityQueue {
    private List<Pair> wordQueue;
    
    public PriorityQueue() {
        this.wordQueue = new ArrayList<>();
    }
    public PriorityQueue(int n) {
        this.wordQueue = new ArrayList<>(n);
    }

    public Pair getPair(int index) {
        return this.wordQueue.get(index);
    }

    public void insertPair(Pair newPair){
        int newValue = newPair.getValue();

        if (this.wordQueue.size() == 0) {
            this.wordQueue.add(newPair);
        } else {
            for(int i = 0; i < this.wordQueue.size(); i++){
                if (this.wordQueue.get(i).getValue() > newValue){
                    this.wordQueue.add(i, newPair);
                    break;
                }
                
                if (i == (this.wordQueue.size() - 1)){
                    this.wordQueue.add(i + 1,newPair);
                    break;
                }
            }   
        }
    }

    public void deletePair(Pair pairToDelete){
        for(int i = 0; i < this.wordQueue.size(); i++){
            if (this.wordQueue.get(i).getNode().isEqual(pairToDelete.getNode())){
                this.wordQueue.remove(i);
                break;
            }
        }
    }

    public boolean isEmpty(){
        return this.wordQueue.size() == 0;
    }

    public void displayWordQueue(){
        System.out.print("[");
        for(int i = 0; i < this.wordQueue.size(); i++){
            this.wordQueue.get(i).displayPair();
            if (i != (this.wordQueue.size() - 1)){
                System.out.print(",");
            }
        }
        System.out.println("]");


    }
}
