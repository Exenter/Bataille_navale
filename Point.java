import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Point {
	int x;
	int y;
	//Set<Integer> verif;
	ArrayList<Integer> verif;
	
	public Point() {
		x = 5;
		y = 5;
		//verif = new HashSet<Integer>();
		verif = new ArrayList<Integer>();
		for(int i =0; i<15; i++) {
			verif.add(i);
			//v.add(i);
			
		}
	}
	
	public Point(int X, int Y) {
		this.x = X;
		this.y = Y;
		verif = new ArrayList<Integer>();
		for(int i =0; i<15; i++) {
			verif.add(i);
		}
	}
	
	public void depX(Sens sens) {
		if (sens == Sens.Avant) 
			if (verif.contains(x+1)) 
				x +=1;
		if (sens == Sens.Arriere) 
			if (verif.contains(x-1)) 
				x -=1;
	}
	
	public void depY(Sens sens) {
		if (sens == Sens.Avant) 
			if (verif.contains(y+1)) 
				y +=1;
		if (sens == Sens.Arriere) 
			if (verif.contains(y-1)) 
				y -=1;
	}
	
	public void rotationX(Point p, String cote) {
		if (cote == "+") {
			if(verif.contains(p.x+1)) {
				this.x = p.x+1;
				
			}
			
		}
		if (cote == "-") {
			if(verif.contains(p.x-1)) {
				this.x = p.x-1;
			}
		}
	}
	
	public void rotationY(Point p, String cote) {
		if (cote == "+") {
			if(verif.contains(p.y+1)) {
				this.y = p.y+1;
			}
			
		}
		if (cote == "-") {
			if(verif.contains(p.y-1)) {
				this.y = p.y-1;
			}
		}
	}
	
}
