import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class CommanderConundrum {

	//A naive solution using a depth first search... Does NOT work on all inputs
	public static boolean decideCommandN(Astronaut commander){
		if(commander.getNoSubordinates()){
			return commander.getVote();
		}
		else{			
			boolean one = decideCommandN(commander.getSub(3));
			boolean two = decideCommandN(commander.getSub(2));
			if(one == two){
				return one;
			}
			else {
				return decideCommandN(commander.getSub(1));
			}
		}
	}
	
	//Randomized depth first search that works on all inputs with high probability
	public static boolean decideCommand(Astronaut commander){
		if(commander.getNoSubordinates()){
			return commander.getVote();
		}
		else{
			Random rn = new Random();
			
			int first = rn.nextInt(3) + 1;
			int second = first;
			while(first == second){
				second = rn.nextInt(3) + 1;
			}
			
			boolean one = decideCommand(commander.getSub(first));
			boolean two = decideCommand(commander.getSub(second));
			if (one == two){
				return one;
			}
			else{
				int third = second;
				while (third == second || third == first){
					third = rn.nextInt(3) + 1;
				}
				return decideCommand(commander.getSub(third));
			}
		}
	}
	
	public static boolean charToBool(char c){
		if (c == 'T'){
			return true;
		}
		else if (c == 'F'){
			return false;
		}
		else {
			throw new IllegalArgumentException("Passed non-T non-F");
		}
	}
	
	public static void main(String[] args) throws IOException {
		//Read in file
		BufferedReader b = new BufferedReader(new FileReader("CommanderConundrumIN.txt"));
		
		while(b.ready()){
			String line = b.readLine();
			LinkedList<Astronaut> next = new LinkedList<Astronaut>();
			LinkedList<Astronaut> last = new LinkedList<Astronaut>();
			
			//Create a linkedlist of the boolean values
			for (char ch : line.toCharArray()){
				next.add(new Astronaut(charToBool(ch)));
			}
			
			//Build the ship's crew from bottom to top
			while(next.size() > 1){
				
				last = next;
				next = new LinkedList<Astronaut>();
				
				//Make a new astronaut at a higher layer
				while(last.size() >= 3){
					Astronaut one = last.pollFirst();
					Astronaut two = last.pollFirst();
					Astronaut three = last.pollFirst();
					
					next.add(new Astronaut(one, two, three));
				}
			}
			
			//Set commander, make a decision, then see if you checked all crewmembers
			Astronaut comm = next.getFirst();
			System.out.println(decideCommand(comm));
			System.out.println(comm.allAccessed());
			System.out.println("-----");
			
		}
		
		b.close();
	}

	
}
