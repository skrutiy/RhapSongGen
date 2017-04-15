import java.util.*;

public class Driver{
   public static void main(String[] args){
      
       Prefix test = new Prefix("this","is");
       
       MarkovChain markov = new MarkovChain();
       markov.addWordsToChain("hello this is this a sentance");
       System.out.println(markov.getWord(test));

   
//     Prefix test = new Prefix("this","is");
//     Prefix test1 = new Prefix("this","is");
//     Suffix testS = new Suffix("yolo");
//     ArrayList<Suffix> lst = new ArrayList<Suffix>();
//     HashMap<Prefix, ArrayList<Suffix>> chain = new HashMap<Prefix, ArrayList<Suffix>>();
//     
//     lst.add(testS);
//     chain.put(test,lst);
//     System.out.println(chain.get(test).get(0).getS());
   }
}