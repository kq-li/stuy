public class LetsMakeADeal{

    // Simulate the Lets Make A Deal Game.
    // Cover the entire sample space to verify 
    // that switching from your first choice doubles
    // you're chances of winning.


    // How to run:   $java LetsMakeADeal trials y/n
    //               if arg[1] is "y" then it prints a run of a single game


    public static void main(String [] args){
	int prizeDoor, firstChoice, secondChoice, doorRevealed;
	int trials = Integer.parseInt(args[0]);

	int winsHolding = 0;
	int winsSwitching = 0;


	for (int i =  0 ; i < trials; i++){
	    // prizeDoor and firstChoice are independently assigned a 0,1 or 2 
	    // with equal probability.
	    prizeDoor = (int)(Math.random() * 3);
	    firstChoice = (int)(Math.random() * 3);
	    

	    // determine door revealed - there are 12 cases
	    // prize : first: revealed
	    // 001,002, 012,102, 021,201, 110, 112, 120,210, 220, 221
	  
	    // 001, 002
	    if (prizeDoor == 0 && firstChoice == 0) doorRevealed = 1 + (int)(Math.random()*2);
	    // 012,102
	    else if (prizeDoor + firstChoice == 1) doorRevealed = 2;
	    //021,201
	    else if ((prizeDoor == 0 && firstChoice == 2)||
		     (prizeDoor == 2 && firstChoice == 0)) doorRevealed = 1;
	    // 110, 112
	    else if (prizeDoor == 1 && firstChoice == 1){
		doorRevealed = 0;
		if (Math.random() < 0.5) doorRevealed = 2;
	    }
	    //120, 210
	    else if (prizeDoor + firstChoice == 3) doorRevealed = 0;
	    // 220 , 221
	    else 
		doorRevealed = (int)(Math.random() * 2);


	    // ********* secondChoice can't be equal to firstChoice nor doorRevealed *****
	    if (doorRevealed == 0){
		if (firstChoice == 1) secondChoice = 2;
		else secondChoice = 1;
	    }
	    else if (doorRevealed == 1){
		if (firstChoice == 0) secondChoice = 2;
		else secondChoice = 0;
	    }	
	    else{ // doorRevealed == 2
		if (firstChoice == 1) secondChoice = 0;
		else secondChoice = 1;
	    }

	    if (prizeDoor == firstChoice) winsHolding++;
	    else if (prizeDoor == secondChoice) winsSwitching++;

	    if (args[1].equals("y"))
		System.out.println("Prize: " + prizeDoor + " 1st choice: " + firstChoice + " door revealed: " + doorRevealed + " 2nd choice: " + secondChoice);

	}

	System.out.println("% wins not switching: " + 1.0*winsHolding/trials);
	System.out.println("% wins switching: " + 1.0*winsSwitching/ trials);

	
    }
    }
