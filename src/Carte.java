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
		taille =InterfaceGraphique.taille;
		cases = new Case[taille][taille] ;
		for(int j=0; j<taille; j++) {
			for(int i=0; i<taille; i++) {
				cases[i][j] = new Case();
			}
		}
	}
	
	public Carte(int taille) {
		listeBatHumain = new ArrayList<Bateaux>();
		listeBatMachine = new ArrayList<Bateaux>();
		this.taille = taille;
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
					cases[i][j].bat = m;
					}
				}
			}
		}

	
	
	public Boolean verifDepPossible(Bateaux b, Sens s) {	//le bateaux a les coordonées de la  position initial
		Boolean depPossible = false;
		Point p1t = new Point(b.p1.x, b.p1.y);
		Point centret = new Point(b.centre.x, b.centre.y);
		Point p2t = new Point(b.p2.x, b.p2.y);
		Bateaux temp = new Bateaux(p1t,centret,p2t,b.pro, b.numero);
		temp.deplacementBateau(s);
		//System.out.println("class carte, x centre temp new coordoné: " +temp.centre.x );
		//System.out.println("class carte, x centre coordoné init bateau: " +b.centre.x);
		if(temp.centre.x != b.centre.x || temp.centre.y != b.centre.y || temp.p1.x != b.p1.x) { /// si le bateaux a bouger
			//System.out.println("true");	
			switch (s) {
				case Avant:
					if(cases[temp.p2.x][temp.p2.y].occupant == Proprio.Libre) {
						depPossible = true;
					}		
					break;
					
				case Arriere:
					if(cases[temp.p1.x][temp.p1.y].occupant == Proprio.Libre) {
						depPossible = true;
					}
					break;
					
				case Rotation:
					depPossible = true;
					if(temp.ori == Orientation.Horizontale) { /// Passage de vert à horiz
						for(int i= temp.p1.x; i< temp.centre.x; i++) {
							if(cases[i][temp.centre.y].occupant == Proprio.Humain || cases[i][temp.centre.y].occupant == Proprio.Machine) {
								depPossible = false;
							}
						}
						for(int i = temp.centre.x+1; i<= temp.p2.x; i++) {
							if(cases[i][temp.centre.y].occupant == Proprio.Humain || cases[i][temp.centre.y].occupant == Proprio.Machine) {
								depPossible = false;
							}
						}
						
					}
					
					else if(temp.ori == Orientation.Verticale) { /// Passage de horiz à vert
						for(int i= temp.p1.y; i< temp.centre.y; i++) {
							if(cases[temp.centre.x][i].occupant == Proprio.Humain || cases[temp.centre.x][i].occupant == Proprio.Machine) {
								depPossible = false;
							}
						}
						for(int i = temp.centre.y+1; i<= temp.p2.y; i++) {
							if(cases[temp.centre.x][i].occupant == Proprio.Humain || cases[temp.centre.x][i].occupant == Proprio.Machine) {
								depPossible = false;
							}
						}
					}
					
					break;

				default:
					System.exit(0);
					break;
			}
			
		}
		return depPossible;
	}

	
	
	public void updateCaseBateau(Bateaux b, Sens s) { //le bateaux a les coordonées de la nouvelle position
		Case temp;
		Case temp2;
		
			if(b.ori == Orientation.Horizontale) {
				switch (s) {
				case Avant :
					temp = cases[b.p1.x - 1][b.p1.y];
					cases[b.p1.x - 1][b.p1.y] = new Case(Proprio.Libre, temp.vision);
					
					cases[b.p2.x][b.p2.y].occupant = b.pro;
					cases[b.p2.x][b.p2.y].bat = b;
					break;
					
				case Arriere :
					temp = cases[b.p2.x + 1][b.p2.y];
					cases[b.p2.x + 1][b.p2.y] = new Case (Proprio.Libre, temp.vision);
					
					cases[b.p1.x][b.p1.y].occupant = b.pro;
					cases[b.p1.x][b.p1.y].bat = b;
					break;
					
				case Rotation: // passage de verticale a horizontale
		
					for(int i = 1; i <= Math.round(((b.taille/2)-0.5)); i++) {
		
		
						temp = cases[b.centre.x][b.centre.y+i];
						cases[b.centre.x][b.centre.y+i] = new Case(Proprio.Libre, temp.vision);
						
						temp2 = cases[b.centre.x][b.centre.y-i];
						cases[b.centre.x][b.centre.y-i] = new Case(Proprio.Libre, temp2.vision);
						
						cases[b.centre.x+i][b.centre.y].occupant = b.pro;
						cases[b.centre.x+i][b.centre.y].bat = b;
						
						cases[b.centre.x-i][b.centre.y].occupant = b.pro;
						cases[b.centre.x-i][b.centre.y].bat = b;
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
					temp = cases[b.p1.x][b.p1.y-1];
					cases[b.p1.x][b.p1.y-1] = new Case(Proprio.Libre, temp.vision);
					
					cases[b.p2.x][b.p2.y].occupant = b.pro;
					cases[b.p2.x][b.p2.y].bat = b;
					break;
					
				case Arriere :
					temp = cases[b.p2.x][b.p2.y+1];
					cases[b.p2.x][b.p2.y+1] = new Case(Proprio.Libre, temp.vision);
					
					cases[b.p1.x][b.p1.y].occupant = b.pro;
					cases[b.p1.x][b.p1.y].bat = b;
					break;
					
				case Rotation: // passage d'horizontale à verticale
		
					for(int i = 1; i <= Math.round(((b.taille/2)-0.5)); i++) {
						
		
						
						temp = cases[b.centre.x+i][b.centre.y];
						cases[b.centre.x+i][b.centre.y] = new Case(Proprio.Libre, temp.vision);
						
						temp2 = cases[b.centre.x-i][b.centre.y];
						cases[b.centre.x-i][b.centre.y] = new Case(Proprio.Libre, temp.vision);
						
						cases[b.centre.x][b.centre.y+i].occupant = b.pro;
						cases[b.centre.x][b.centre.y+i].bat = b;
						
						cases[b.centre.x][b.centre.y-i].occupant = b.pro;
						cases[b.centre.x][b.centre.y-i].bat = b;

					}		
					
					break;
				
				default:
					System.exit(0);
					break;
				}
			}
		}
	

	public void updateVisibilite(Bateaux b, Sens s) { //le bateaux a les coordonées de la nouvelle position
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
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire; //(41)
				}
				if(b.p2.x+1<taille) {  /// nouvelle pos n 'est pas sur le bord droit
					cases[b.p1.x+1][b.p1.y-1].vision = Vision.Claire; //(42)
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



	public void tire(int x, int y, Joueur j, Joueur H) {
		int ind_bat=0;
		if(cases[x][y].occupant == Proprio.Humain && j.nature == Proprio.Machine) {
			System.out.println("nique toi");
			
			for(int i=H.listeBat.size()-1; i>=0; i--){	//for(Bateaux b:H.listeBat) {
				Bateaux b = H.listeBat.get(i);
				if(b == cases[x][y].bat) { //verifie que le bateaux de la case est dans la liste
					b.updateVie();
					ind_bat=H.listeBat.indexOf(b);
					System.out.println("hp:"+b.vie);
					j.updateScore();
					

					if(b.vie == 0) {
						for(int jj = b.p1.x; jj<=b.p2.x; jj++) {
							for(int ii = b.p1.y; ii<=b.p2.y; ii++) {
								cases[jj][ii].occupant = Proprio.Libre;
							}
						}
						int ind = b.numero;
						switch (ind) {
						case 1:
							InterfaceGraphique.ship1.setEnabled(false);
							break;
						case 2:
							InterfaceGraphique.ship2.setEnabled(false);
							break;
						case 3:
							InterfaceGraphique.ship3.setEnabled(false);
							break;
							
						}
						listeBatHumain.remove(b);
						H.listeBat.remove(b);
						H.listePoint.remove(b.p1);
						H.listePoint.remove(b.centre);
						H.listePoint.remove(b.p2);
						
					}
				}
			}	
		}
	}
	
////UNDER CONSTRUCTION /////		//// UNDER CONSTRUCTION /////		//// UNDER CONSTRUCTION /////
	
//	public boolean verifTirePossible(int x, int y, Joueur j) {
//	boolean b = true;
//	if((cases[x][y].occupant == Proprio.Humain && j.nature == Proprio.Humain) || (cases[x][y].occupant == Proprio.Machine && j.nature == Proprio.Machine) ) {
//		b = false;
//	}
//	if(x<taille)
//}
}
