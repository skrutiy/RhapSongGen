import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;

public class Driver{


	public static void main(String[] args){
	
		Scanner in = new Scanner(System.in);
	
		//System.out.println("Enter Song Title: ");
      String name = JOptionPane.showInputDialog(null,
                                                "Enter Song Title:",
                                                "Rap Song Generator",
                                                JOptionPane.INFORMATION_MESSAGE);
      if (name == null) {
         System.exit(0);
      }
		
		//String name = in.nextLine();
      String songName = name;
      name = name.toLowerCase();
		String[] words = name.split(" ");
		
		
		MarkovChain chain = read(new File("Files/AllSongs.txt"), words);
      
      /*
      String song = "";
      
		for(int i=0; i<20; i++){
			song += chain.getSentence(20) + "\n";
         //System.out.println(chain.getSentence(20));
		}
      */
      
      String[] finalArr;
      finalArr = chain.getRhyming(15,10);
      
      String song = "";
      for(int i=0; i<finalArr.length; i++){
         song = (song += (finalArr[i] + "\n"));
      }
      
      JFrame frame = new JFrame();
      frame.setTitle("Your Song: " + songName);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JTextArea textArea = new JTextArea();
      textArea.setText(song);
      textArea.setRows(25);
      textArea.setColumns(45);
      textArea.setEditable(false);
      JScrollPane scrollPane = new JScrollPane(textArea);
      frame.add(scrollPane);
      frame.pack();
      
      JOptionPane.showMessageDialog(frame,
                                    scrollPane,
                                    "Your song: " + songName,
                                    JOptionPane.INFORMATION_MESSAGE);
      System.exit(0);
      
	
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
			return markov;
		}
		catch(FileNotFoundException e){
			System.out.println("file not found");
		}
		return markov;
	}
}