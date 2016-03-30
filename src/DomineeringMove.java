

public class DomineeringMove {
	/*
	 * each move consists of two points on the board being taken by a domino 
	 */
	public final int x1;
	public final int x2;
	public final int y1;
	public final int y2;
	
	public DomineeringMove(int x1, int y1, int x2, int y2 ){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		
		}

	public boolean equals(DomineeringMove move2){
		if( (x1 == move2.x1 && y1 == move2.y1 && x2 == move2.x2 && y2 == move2.y2 ) ||
			(x1 == move2.x2 && y1 == move2.y2 && x2 == move2.x2 && y1 == move2.y1)){
			return true;
		}
		return false;
	}
	@Override 
	public int hashCode(){
		return toString().hashCode();
	}
	public String toString(){		
		return "move at points: ("+  x1 + ", " + y1 + ") (" + x2 + ", " + y2 +  ")";
	}
}

