import java.util.*;

public class Driver{
   public static void main(String[] args){
      
       Prefix test = new Prefix("this","is");
       
       MarkovChain markov = new MarkovChain();
       markov.addWordsToChain("hello this is this a sentance and this is another sentance");
       System.out.println(markov.getWord(test));
   }
}