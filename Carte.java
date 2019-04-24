import java.util.*;

public class Carte {
	int taille;
	Case cases[][];
	ArrayList<Bateaux> listeBatHumain;
	ArrayList<Bateaux> listeBatMachine;
	
	///// Constructeur ////
	public Carte() {
		listeBatHumain = new ArrayList<Bateaux>();
		listeBatMachine = new ArrayList<Bateaux>();
		taille =15;
		cases = new Case[taille][taille] ;
	}
	
	
	///// Fonction ////
	public void addBateaux (Bateaux b) {
		if(b.pro == Proprio.Humain) {
			listeBatHumain.add(b);
		}
		else {
			listeBatMachine.add(b);
		}
		
		setCaseBateau();
	}

	
	
	public void setCaseBateau() {
		for(Bateaux b:listeBatHumain) {
			for(int i = b.p1.x; i<b.p2.x+1; i++) {   /// Determination cases bateaux
				for(int j = b.p1.y; j<b.p2.y+1; j++) {
					cases[i][j].occupant = Proprio.Humain;
					cases[i][j].vision = Vision.Claire;
					cases[i][j].bat = b;
				}
			}
			
			for(int i = b.p1.x-1; i <= b.p2.x+1; i++ ) { /// Determiner vision autour ////// /!\ ATENTION ne pas metre 2 bateau humain a coté au debut /!\ !!!!!!///////
				for(int j = b.p1.y-1; j <= b.p2.y+1; j++ ) {
					if (0<i && i<taille && 0<j && j<taille) {
						if (cases[i][j].occupant != Proprio.Humain && cases[i][j].vision == Vision.Brouillard) {
							cases[i][j].vision = Vision.Claire;
						}
					}
				}
			}
		}
		
		for(Bateaux m:listeBatMachine) {
			for(int i = m.p1.x; i<=m.p2.x; i++) {
				for(int j = m.p1.y; j<=m.p2.y; j++) {						
					cases[i][j].occupant = Proprio.Machine;
					}
				}
			}
		}

				
	public void updateCaseBateau(Bateaux b, Sens s) { //le bateaux a les coordonées de la nouvelle position
			if(b.ori == Orientation.Horizontale) {
				switch (s) {
				case Avant :
					cases[b.p1.x - 1][b.p1.y].occupant = Proprio.Libre;
					cases[b.p2.x][b.p2.y].occupant = b.pro;
					break;
				case Arriere :
					cases[b.p2.x + 1][b.p2.y].occupant = Proprio.Libre;
					cases[b.p1.x][b.p1.y].occupant = b.pro;
					break;
				case Rotation: // passage de verticale à horizontale
					for(int i = 1; i <= b.taille/2-0.5; i++) {
						cases[b.centre.x][b.centre.y+i].occupant = Proprio.Libre;
						cases[b.centre.x][b.centre.y-i].occupant = Proprio.Libre;
						
						cases[b.centre.x+i][b.centre.y].occupant = b.pro;
						cases[b.centre.x-i][b.centre.y].occupant = b.pro;
					}
					break;
				default:
					System.exit(0);
					break;
				}
			}			
			
			if(b.ori == Orientation.Verticale) {
				switch (s) {
				case Avant:
					cases[b.p1.x][b.p1.y-1].occupant = Proprio.Libre;
					cases[b.p2.x][b.p2.y].occupant = b.pro;
					break;
				case Arriere :
					cases[b.p2.x][b.p2.y-1].occupant = Proprio.Libre;
					cases[b.p1.x][b.p1.y].occupant = b.pro;
					break;
				case Rotation: // passage d'horizontale à verticale
					for(int i = 1; i <= b.taille/2-0.5; i++) {
						cases[b.centre.x][b.centre.y+i].occupant = b.pro;
						cases[b.centre.x][b.centre.y-i].occupant = b.pro;
						
						cases[b.centre.x+i][b.centre.y].occupant = Proprio.Libre;
						cases[b.centre.x-i][b.centre.y].occupant = Proprio.Libre;
					}		
					
					break;
				
				default:
					System.exit(0);
					break;
				}
			}
		}
	

	public void updateVisibilité(Bateaux b, Sens s) { //le bateaux a les coordonées de la nouvelle position
		if(b.pro == Proprio.Humain) {
			if(b.ori == Orientation.Horizontale && s == Sens.Avant && b.p2.x+1<taille) {/// nouvelle pos n'est pas sur le bord droit 
				cases[b.p2.x+1][b.p2.y].vision = Vision.Claire; //(1)
				if(b.p2.y-1>0) { /// nouvelle pos n 'est pas sur le bord bas
					cases[b.p2.x+1][b.p2.y-1].vision = Vision.Claire; //(11)
				}
				if(b.p2.y+1<taille) { /// nouvelle pos n 'est pas sur le bord haut
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire; //(12) 
				}
			}

			else if(b.ori == Orientation.Horizontale && s == Sens.Arriere && b.p1.x-1>0) { /// nouvelle pos n'est pas sur le bord gauche
				cases[b.p1.x-1][b.p1.y].vision = Vision.Claire; //(2)
				if(b.p1.y-1>0) { /// nouvelle pos n 'est pas sur le bord bas
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire; //(21)
				}
				if(b.p1.y+1<taille) { /// nouvelle pos n 'est pas sur le bord haut
					cases[b.p1.x-1][b.p1.y+1].vision = Vision.Claire; //(22)
				}		
			}
			
			else if(b.ori == Orientation.Verticale && s == Sens.Avant && b.p2.y+1<taille) {  /// nouvelle pos n 'est pas sur le bord haut
				cases[b.p2.x][b.p2.y+1].vision = Vision.Claire; //(3)
				if(b.p2.x-1>0) {  /// nouvelle pos n 'est pas sur le bord gauche
					cases[b.p2.x-1][b.p2.y+1].vision = Vision.Claire; //(31)
				}
				if(b.p2.x+1<taille) {  /// nouvelle pos n 'est pas sur le bord droit
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire; //(32)
				} 
			}

			else if(b.ori == Orientation.Verticale && s == Sens.Arriere && b.p1.y-1>0) {  /// nouvelle pos n 'est pas sur le bord bas
				cases[b.p1.x][b.p1.y-1].vision = Vision.Claire; //(4)
				if(b.p1.x-1>0) {  /// nouvelle pos n 'est pas sur le bord gauche
					cases[b.p1.x-1][b.p1.y+1].vision = Vision.Claire; //(41)
				}
				if(b.p2.x+1<taille) {  /// nouvelle pos n 'est pas sur le bord droit
					cases[b.p1.x+1][b.p1.y+1].vision = Vision.Claire; //(42)
				}
			}
			else if(b.ori == Orientation.Horizontale && s == Sens.Rotation ) { /// passage de vert à horiz 
				for(int i= b.p1.x; i<= b.p2.x; i++) {
					cases[i][b.centre.y + 1].vision = Vision.Claire;// (5)
					cases[i][b.centre.y - 1].vision = Vision.Claire; //(5)
				}
				if(b.p2.x+1<taille) { /// nouvelle pos n'est pas sur le bord droit 
					cases[b.p2.x+1][b.p2.y-1].vision = Vision.Claire; //(51)
					cases[b.p2.x+1][b.p2.y].vision = Vision.Claire; //(51)
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire; //(51)
				}
				if(b.p1.x-1>0) { /// nouvelle pos n'est pas sur le bord gauche
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire; //(52)
					cases[b.p1.x-1][b.p1.y].vision = Vision.Claire; //(52)
					cases[b.p1.x-1][b.p1.y+1].vision = Vision.Claire; //(52)
				}
			}
			else if(b.ori == Orientation.Verticale && s == Sens.Rotation ) { /// passage de hori à vert
				for(int i= b.p1.y; i<= b.p2.y; i++) {
					cases[b.centre.x + 1][i].vision = Vision.Claire;// (6)
					cases[b.centre.x - 1][i].vision = Vision.Claire; //(6)
				}
				if(b.p2.y+1<taille) { /// nouvelle pos n'est pas sur le bord haut 
					cases[b.p2.x-1][b.p2.y+1].vision = Vision.Claire; //(61)
					cases[b.p2.x][b.p2.y+1].vision = Vision.Claire; //(61)
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire; //(61)
				}
				if(b.p1.y-1>0) { /// nouvelle pos n'est pas sur le bord bas
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire; //(62)
					cases[b.p1.x][b.p1.y-1].vision = Vision.Claire; //(62)
					cases[b.p1.x+1][b.p1.y-1].vision = Vision.Claire; //(62)
				}
			}
		}

	}	


	public Boolean verifDepPossible(Bateaux b, Sens s) {
		Boolean depPossible = false;
		Bateaux temp = b;
		temp.deplacementBateau(s);
		
		if(temp.centre.x != b.centre.x || temp.centre.y != b.centre.y || temp.p1.x != b.p1.x) { /// si le bateaux a bouger
			if(temp.ori == Orientation.Horizontale) {
				switch (s) {
				case Avant:
					if(cases[temp.p2.x][temp.p2.y].occupant == Proprio.Libre) {
						depPossible = true;
					}		
					break;
				case Arriere:
					
					break;
				case Rotation:
					
					break;

				default:
					System.exit(0);
					break;
				}
			}
			
			else if(temp.ori == Orientation.Verticale) {
				switch (s) {
				case Avant:
					
					break;
				case Arriere:
					
					break;
				case Rotation:
					
					break;

				default:
					System.exit(0);
					break;
				}
			}
		}
		
		
		
		
		
		return depPossible;
	}




}
