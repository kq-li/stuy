
public class Astronaut {

	private Astronaut one, two, three;
	private boolean noSubordinates;
	private boolean vote;
	private boolean accessed;
	
	public Astronaut(Astronaut s1, Astronaut s2, Astronaut s3){
		one = s1;
		two = s2;
		three = s3;
		noSubordinates = false;
		vote = false;
		accessed = false;
	}
	
	public Astronaut(boolean myVote){
		one = null;
		two = null;
		three = null;
		noSubordinates = true;
		vote = myVote;
		accessed = false;
	}
	
	public boolean getVote() {
		accessed = true;
		if(noSubordinates){
			return vote;
		}
		throw new IllegalArgumentException("Tried to get vote of astronaut with subordinate");
	}
	
	public boolean getNoSubordinates() {
		accessed = true;
		return noSubordinates;
	}

	public Astronaut getSub(int i) {
		accessed = true;
		if (i == 1){
			return one;
		}
		else if (i == 2){
			return two;
		}
		else if (i == 3){
			return three;
		}
		else{
			return null;
		}
	}

	public boolean allAccessed() {
		if(noSubordinates){
			return accessed;
		}
		else {
			return one.allAccessed() && two.allAccessed() && three.allAccessed();
		}
	}
	
}
