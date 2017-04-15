import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Driver{
     
      
	public static void main(String[] args){
      
		Scanner in = new Scanner(System.in);
      
      System.out.println("Enter Song Title: ");
		
      String name = in.nextLine();
		String[] words = name.split(" ");
		MarkovChain chain = read(new File("Files/DAMN.txt"), words);
            
      System.out.println(name + "\n By, Timmy Mac");
       
      /*       
      for(int i=0; i<20; i++){
         System.out.println(chain.getSentence(10));
      }
      */
      
      
      String[] finalArr;
      finalArr = chain.getRhyming(15,10);
      
      for(int i=0; i<finalArr.length; i++){
         System.out.println(finalArr[i]);
      }
      
	}
	
	public static MarkovChain read(File file, String[] words){
      
      MarkovChain markov = new MarkovChain();
      
		try{
			Scanner scan = new Scanner(file);
			//String curr = scan.next();
         String curr = "";
			String prev = "";
			while(scan.hasNextLine()){
				prev = curr;
				curr = scan.nextLine();
				for(int i = 0; i < words.length; i++){
					//if(curr.contains(words[i])){
               if(curr.contains(" " + words[i]) || curr.contains(words[i] + " ")){
						if(!prev.equals(" ") && prev != null)
							markov.addWordsToChain(prev);
						markov.addWordsToChain(curr);
						if(scan.hasNext())
							markov.addWordsToChain(scan.nextLine());
						break;
					}
				}
			}
         return markov;
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
		}
      return markov;
	}
}