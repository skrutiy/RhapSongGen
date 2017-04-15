import java.util.*;

public class MarkovChain{
   
   static Prefix oldPre;
   static Suffix oldSuf;
   HashMap<Prefix, ArrayList<Suffix>> chain;
   
   public MarkovChain(){
      oldPre = new Prefix();
      oldSuf = new Suffix();
      chain = new HashMap<(String,String), ArrayList<Suffix>>();
   }
      
   public void addWordsToChain(String str){
      String[] words = str.split("");
      for(int i = 0; i<words.length; i++){
         addToChain(words[i]);
      }
   }
   
   public void addToChain(String str){
      Prefix pre = new Prefix();
      Suffix suf = new Suffix();
      
      pre.setL(oldPre.getR());
      pre.setR(oldSuf.getS());
      suf.setS(str);
      
      
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
      
      oldPre.setL(oldPre.getL());
      oldPre.setR(oldPre.getR());
      oldSuf.setS(suf.getS());
      
      Set set = chain.entrySet();
          Iterator it = set.iterator();
           while (it.hasNext()) {
               Map.Entry entry = (Map.Entry) it.next();
               System.out.println(entry.getKey() + " : " + entry.getValue());
     }

      
   }
   
   public String getWord(Prefix key){
      //output first but need random
      ArrayList<Suffix> x = chain.get(key);
      System.out.println(x.size());
      return "end of stufffff";
   }
   
   public void addThreeToChain(String str1, String str2, String str3){
      addToChain(str1);
      addToChain(str2);
      addToChain(str3);
   }
   
//    public void shift(String str){
//       pre.setL(pre.getR());
//       pre.setR(suf.getS());
//       suf.setS(str);
//    }
}
