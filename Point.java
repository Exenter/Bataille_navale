import java.util.HashSet;
import java.util.Set;

public class Point {
	int x;
	int y;
	Set<Integer> verif = new HashSet<Integer>();
	
	public Point() {
		x = 5;
		y = 5;
	}
	
	public Point(int X, int Y) {
		this.x = X;
		this.y = Y;
	}
	
	public void depX(String sens) {
		if (sens == "avant") 
			if (verif.contains(x+1)) 
				x +=1;
		if (sens == "arriere") 
			if (verif.contains(x-1)) 
				x -=1;
	}
	
	public void depY(String sens) {
		if (sens == "avant") 
			if (verif.contains(y+1)) 
				y +=1;
		if (sens == "arriere") 
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
