import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Point {
	int x;
	int y;
	ArrayList<Integer> verif;
	
	////CONSTRUCTEUR\\\\
	public Point() {
		x = 5;
		y = 5;
		verif = new ArrayList<Integer>();
		for(int i =0; i<InterfaceGraphique.taille; i++) {
			verif.add(i);
						
		}
	}
	
	public Point(int X, int Y) {
		this.x = X;
		this.y = Y;
		verif = new ArrayList<Integer>();
		for(int i =0; i<InterfaceGraphique.taille; i++) {
			verif.add(i);
		}
	}
	
	////FONCTIONS\\\\
	public void depX(Sens sens) { //Deplacement horizontale du point
		if (sens == Sens.Avant) 
			if (verif.contains(x+1)) 
				x +=1;
		if (sens == Sens.Arriere) 
			if (verif.contains(x-1)) 
				x -=1;
	}
	
	public void depY(Sens sens) { //Deplacement veriticale du point
		if (sens == Sens.Avant) 
			if (verif.contains(y+1)) 
				y +=1;
		if (sens == Sens.Arriere) 
			if (verif.contains(y-1)) 
				y -=1;
	}
	
	
	// deplacement du point par rapport a un autre
	public void rotationX(Point p, String cote) {//Changement du x
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
	
	public void rotationY(Point p, String cote) {//Changement du y
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
