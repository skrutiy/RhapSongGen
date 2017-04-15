
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

public class Driver{


	public static void main(String[] args){
	
		Scanner in = new Scanner(System.in);
	
		System.out.println("Enter Song Title: ");
		
		String name = in.nextLine();
		String[] words = name.split(" ");
		
		
		MarkovChain chain = read(new File("Files/AllSongs.txt"), words);

		for(int i=0; i<20; i++){
			System.out.println(chain.getSentence(20));
		}
	
	}

	public static MarkovChain read(File file, String[] words){
		int cntr = 0;
		MarkovChain markov = new MarkovChain();

		try{
			Scanner scan = new Scanner(new BufferedReader(new FileReader(file)));
			String curr = "";
			String prev = "";
			while(scan.hasNextLine()){
				cntr++;
				prev = curr;
				curr = scan.nextLine();
				if(cntr % 100 == 0){
					markov.addWordsToChain(curr);
				}
				for(int i = 0; i < words.length; i++){
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
			System.out.println(cntr);
			System.out.println(scan.hasNextLine());
			return markov;
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
		}
		return markov;
	}
}