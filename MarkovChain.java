import java.util.*;

public class MarkovChain{

   HashMap<Prefix, ArrayList<Suffix>> chain;
   char[] replaceThese = {'(', ')'};
   
   public MarkovChain(){
      chain = new HashMap<Prefix, ArrayList<Suffix>>();
   }

   public void addWordsToChain(String input){
      
      char[] temp = input.toCharArray();
	   for(int i = 0; i < temp.length; i++){
		  if(temp[i] == '(' || temp[i] == ')' || temp[i] == '\"'){
			  temp[i] = '#';
		  }
	   }
	   String str = "";
	   for(int i = 0; i < temp.length; i++){
	 	  if(temp[i] != '#'){
			   str+=temp[i];
		   }
	   }
      
      //String str = input;
      
      String[] words = str.trim().split(" ");

      
      if(words.length <2) return;
      
      addToChain(new Prefix("\n","\n"), new Suffix(words[0]));
      addToChain(new Prefix("\n",words[0]), new Suffix(words[1]));
      
      for(int i=0; i<words.length-2; i++){
         addToChain(new Prefix(words[i],words[i+1]), new Suffix(words[i+2]));
      }
      
     // addToChain(new Prefix(words[words.length-2],words[words.length-1]), new Suffix("\n"));
     // addToChain(new Prefix(words[words.length-1],"\n"), new Suffix("\n"));
      
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
   
   public String[] getRhyming(int numLines, int numWords){
      
      int max = (numLines*50);
      
      ArrayList<String> preRap = new ArrayList<String>();
      
      int filledLines = 0;
      String[] rap = new String[numLines];
      
      
      while(filledLines < numLines-1 && preRap.size() < max){
      
         String line = getSentence(numWords);
         //generate new line
         
         
         preRap.add(0,line);
         for(int i=0; i<preRap.size()-1; i++){
            int x = compEnd(preRap.get(i),preRap.get(i+1));
            if(x == 0){
               if(preRap.get(i).trim().length() == preRap.get(i+1).trim().length()){
                  preRap.remove(i+1);
               }
               else{
                  rap[filledLines] = preRap.remove(i);
                  rap[filledLines+1] = preRap.remove(i);
                  filledLines = filledLines + 2;
               }
            }
            else if(x < 0){
               break;
            }
            else{
               String temp = preRap.get(i);
               preRap.set(i,preRap.get(i+1));
               preRap.set(i+1,temp);
            }
         }
         
         //scan through array until match or right spot
         //see if it rhymes with one after
         //add to rap and remove if does
         //else repeat
      }
      
      if(rap[rap.length-1] == null){
         rap[rap.length-1] = getSentence(numWords);
      }
      
      return rap;
      
   }
   
   public int compEnd(String str1, String str2){
      String end1 = str1.substring(str1.length()-2,str1.length());
      String end2 = str2.substring(str2.length()-2,str2.length());
      return end1.compareTo(end2);
   }
   
   public String getSentence(int numWordsIn){
   
      int numWords = numWordsIn -1;
      
      String line = "";
      
      Prefix prefix = new Prefix("\n","\n");
      Suffix suffix = new Suffix("");
      
      while(line.trim().length()<1){
        
         for(int i=0; i<numWords; i++){
            String s = getWord(prefix);
            if(s == "\n") s = getWord(prefix);
            suffix.setS(s);
            prefix.setL(prefix.getR());
            prefix.setR(suffix.getS());
            line = line + " " + suffix.getS();
         }
         
         line = line.trim();
      }
      String[] goodEnd = line.split(" ");
      String lastWord = goodEnd[goodEnd.length-1];
      
      String[] badWordString = {"the", "an", "a", "is"};
      
      for(int i = 0; i < badWordString.length; i++){
         if(lastWord.equals(badWordString[i])){
            String s = getWord(prefix);
            if(s == "\n") s = getWord(prefix);
            suffix.setS(s);
            prefix.setL(prefix.getR());
            prefix.setR(suffix.getS());
            line = line + " " + suffix.getS();  
            break;       
         }
      }
 /*     
      while(badWordString.contains(" " + goodEnd[goodEnd.length-1] + " ")){
         String s = getWord(prefix);
            if(s == "\n") s = getWord(prefix);
            suffix.setS(s);
            prefix.setL(prefix.getR());
            prefix.setR(suffix.getS());
            line = line + " " + suffix.getS();
      }
  */    
      
      return line;
   }
   
   public String getWord(Prefix key){
      ArrayList<Suffix> retArr = chain.get(key);
      if(retArr == null){
         return "";
      }
      else{
         Random rand = new Random();
         int randomInt = rand.nextInt(retArr.size());
         return retArr.get(randomInt).getS();
      }
   }
   
}