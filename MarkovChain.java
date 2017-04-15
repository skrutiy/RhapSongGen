import java.util.*;

public class MarkovChain{

   HashMap<Prefix, ArrayList<Suffix>> chain;
   
   public MarkovChain(){
      chain = new HashMap<Prefix, ArrayList<Suffix>>();
   }

   public void addWordsToChain(String str){
      
      String[] words = str.split(" ");
      
      if(words.length <2) return;
      
      addToChain(new Prefix("\n","\n"), new Suffix(words[0]));
      addToChain(new Prefix("\n",words[0]), new Suffix(words[1]));
      
      for(int i=0; i<words.length-2; i++){
         addToChain(new Prefix(words[i],words[i+1]), new Suffix(words[i+2]));
      }
      
      addToChain(new Prefix(words[words.length-2],words[words.length-1]), new Suffix("\n"));
      addToChain(new Prefix(words[words.length-1],"\n"), new Suffix("\n"));
      
   }
   
   public void addToChain(Prefix pre, Suffix suf){
      ArrayList<Suffix> test = chain.get(pre);
      if(test == null){
         ArrayList<Suffix> arr = new ArrayList<Suffix>();
         arr.add(suf);
         chain.put(pre,arr);
      }
      else{
         ArrayList<Suffix> arr = chain.get(pre);
         arr.add(suf);
         chain.put(pre,arr);
      } 
   }
   
   public String getSentence(int numWords){
      
      String line = "";
   
      Prefix prefix = new Prefix("\n","\n");
      Suffix suffix = new Suffix("");
      
      for(int i=0; i<numWords; i++){
         suffix.setS(getWord(prefix));
         prefix.setL(prefix.getR());
         prefix.setR(suffix.getS());
         line = line + " " + suffix.getS();
      }
      
      return line;
   }
   
   public String getWord(Prefix key){
      ArrayList<Suffix> retArr = chain.get(key);
      if(retArr == null){
         return "\n";
      }
      else{
         Random rand = new Random();
         int randomInt = rand.nextInt(retArr.size());
         return retArr.get(randomInt).getS();
      }
   }
   
}