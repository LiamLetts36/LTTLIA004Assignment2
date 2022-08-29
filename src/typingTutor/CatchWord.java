package typingTutor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicBoolean;

//Thread to monitor the word that has been typed.
public class CatchWord extends Thread {
	String target;
	static AtomicBoolean done ; //REMOVE
	static AtomicBoolean pause; //REMOVE
	
	private static  FallingWord[] words; //list of words
	private static int noWords; //how many
	private static Score score; //user score
	
	CatchWord(String typedWord)  {
		target=typedWord;
	}
	
	public static void setWords(FallingWord[] wordList) {
		words=wordList;	
		noWords = words.length;
	}
	
	public static void setScore(Score sharedScore) {
		score=sharedScore;
	}
	
	public static void setFlags(AtomicBoolean d, AtomicBoolean p) {
		done=d;
		pause=p;
	}

	//Creating a class to sort the words in the, FallingWords[], words array using Comparator interface
	class  SortByYValue implements Comparator <FallingWord>{
		//Sorts the words in the array by descending Y value order.
		public int compare(FallingWord one, FallingWord two){
			return two.getY()-one.getY();
		}
	}
	//Method to order words by Descending Y value in original words array
	public synchronized FallingWord[] setWordsByYOrder(FallingWord[] words){
		//Gets length of array
		int numWords = words.length;

		//Storing array values into a list for easier sorting
		ArrayList<FallingWord> wordsList = new ArrayList<>();
		for(int i =0; i < numWords; i++){
			wordsList.add(words[i]);
		}
		//Sorting array using Collections.sort and comparator
		Collections.sort(wordsList, new SortByYValue());

		//Setting new sorted order for original array in descending Y order
		for (int x = numWords-1 ; x >= 0; x--){
			System.out.println(words[x].getY());
			words[x] = wordsList.get(x);
		}
		return words;
	}
	
	public void run() {
		//Ensuring that the word closest to the red zone gets caught first if duplicate words appear on screen
		words = setWordsByYOrder(words);
		int i=0;
		while (i<noWords) {		
			while(pause.get()) {};
			if (words[i].matchWord(target)) {
				System.out.println( " score! '" + target); //for checking
				score.caughtWord(target.length());	
				//FallingWord.increaseSpeed();
				break;
			}
		   i++;
		}
		
	}	
}
