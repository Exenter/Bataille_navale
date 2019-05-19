import java.util.*;

public class Carte {
	int taille;
	Case cases[][];
	ArrayList<Bateaux> listeBatHumain;
	ArrayList<Bateaux> listeBatMachine;
	
	///// CONSTRUCTEUR \\\\\
	public Carte() {
		listeBatHumain = new ArrayList<Bateaux>();
		listeBatMachine = new ArrayList<Bateaux>();
		taille = InterfaceGraphique.taille;
		cases = new Case[taille][taille];
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
		for(int j=0; j<taille; j++) {
			for(int i=0; i<taille; i++) {
				cases[i][j] = new Case();
			}
		}
	}
	
	
	///// FONCTIONS \\\\
	public void addBateaux (Bateaux b) { // Ajout du bateau a la carte
		if(b.pro == Proprio.Humain) {
			listeBatHumain.add(b);
		}
		else {
			listeBatMachine.add(b);
		}
		
		setCaseBateau();
	}

	
	
	public void setCaseBateau() { // Changer l'etat des cases en fonction des position et proprietaires des bateaux
		for(Bateaux b:listeBatHumain) {
			for(int i = b.p1.x; i<b.p2.x+1; i++) {   // Determination occupant des cases 
				for(int j = b.p1.y; j<b.p2.y+1; j++) {
					cases[i][j].occupant = Proprio.Humain;
					cases[i][j].vision = Vision.Claire;
					cases[i][j].bat = b;
				}
			}
			
			for(int i = b.p1.x-1; i <= b.p2.x+1; i++ ) { /// Determiner vision autour des bateaux humains ////// /!\ ATENTION ne pas metre 2 bateau humain a coté au debut /!\ !!!!!!///////
				for(int j = b.p1.y-1; j <= b.p2.y+1; j++ ) {
					if (0<i && i<taille && 0<j && j<taille) { //Verification afin de ne pas sortir du cadre
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

	
	
	public Boolean verifDepPossible(Bateaux b, Sens s) {	//le bateaux a les coordonées de la  position initial, fonction a utiliser avant tout deplacement de bateau
		Boolean depPossible = false;
		
		Point p1t = new Point(b.p1.x, b.p1.y);
		Point centret = new Point(b.centre.x, b.centre.y);
		Point p2t = new Point(b.p2.x, b.p2.y);
		
		Bateaux temp = new Bateaux(p1t,centret,p2t,b.pro, b.numero); // Bateaux temporaire
		temp.deplacementBateau(s); // Bateaux temporaire a les nouvelles position potentiel du bateau a deplacer
		
		if(temp.centre.x != b.centre.x || temp.centre.y != b.centre.y || temp.p1.x != b.p1.x) { /// si temp a bouger
				
			switch (s) { // Verification que les nouvelles positions sont libres
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
					if(temp.ori == Orientation.Horizontale) { /// Passage de verticale a horizontale
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
					
					else if(temp.ori == Orientation.Verticale) { /// Passage de horizontale a verticale
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

	
	
	public void updateCaseBateau(Bateaux b, Sens s) { //le bateaux a les coordonées de la nouvelle position, mise a jour des occupant des cases de la carte
		Case temp;
		Case temp2;
		
			if(b.ori == Orientation.Horizontale) {
				switch (s) {
				case Avant :
					temp = cases[b.p1.x - 1][b.p1.y]; // Case precedemment occupe 
					cases[b.p1.x - 1][b.p1.y] = new Case(Proprio.Libre, temp.vision); //Case precedemment occupe est devenu libre, n'a plus de bateau mais conserve la meme visibilite 
					
					cases[b.p2.x][b.p2.y].occupant = b.pro; // nouvelle case occupe l'est par un humain ou une machine
					cases[b.p2.x][b.p2.y].bat = b; // nouvelle case occupe recupere le bateau
					break;
					
				case Arriere : // (idem que precedemment)
					temp = cases[b.p2.x + 1][b.p2.y];
					cases[b.p2.x + 1][b.p2.y] = new Case (Proprio.Libre, temp.vision);
					
					cases[b.p1.x][b.p1.y].occupant = b.pro;
					cases[b.p1.x][b.p1.y].bat = b;
					break;
					
				case Rotation: // passage de verticale a horizontale
						// (idem que precedemment)
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
				switch (s) {// (idem que precedemment)
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
	

	public void updateVisibilite(Bateaux b, Sens s) { //le bateaux a les coordonées de la nouvelle position et le sens de deplacement subit
		if(b.pro == Proprio.Humain) {
			if(b.ori == Orientation.Horizontale && s == Sens.Avant && b.p2.x+1<taille) {/// nouvelle pos n'est pas sur le bord droit 
				cases[b.p2.x+1][b.p2.y].vision = Vision.Claire; //La vision devant le bateau s'eclaire
				if(b.p2.y-1>0) { /// nouvelle pos n 'est pas sur le bord bas
					cases[b.p2.x+1][b.p2.y-1].vision = Vision.Claire; //La vision devant et en bas s'eclaire
				}
				if(b.p2.y+1<taille) { /// nouvelle pos n 'est pas sur le bord haut
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire; //La vision devant et en haut s'eclaire
				}
			}

			else if(b.ori == Orientation.Horizontale && s == Sens.Arriere && b.p1.x-1>=0) { /// nouvelle pos n'est pas sur le bord gauche
				cases[b.p1.x-1][b.p1.y].vision = Vision.Claire; //La vision deriere s'eclaire
				if(b.p1.y-1>0) { /// nouvelle pos n 'est pas sur le bord bas
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire; //La vision deriere et en bas s'eclaire
				}
				if(b.p1.y+1<taille) { /// nouvelle pos n 'est pas sur le bord haut
					cases[b.p1.x-1][b.p1.y+1].vision = Vision.Claire; //La vision deriere et en haut s'eclaire
				}		
			}
			
			else if(b.ori == Orientation.Verticale && s == Sens.Avant && b.p2.y+1<taille) {  /// nouvelle pos n 'est pas sur le bord haut
				cases[b.p2.x][b.p2.y+1].vision = Vision.Claire; //(idem que precedemment)
				if(b.p2.x-1>0) {  /// nouvelle pos n 'est pas sur le bord gauche
					cases[b.p2.x-1][b.p2.y+1].vision = Vision.Claire; //(idem que precedemment)
				}
				if(b.p2.x+1<taille) {  /// nouvelle pos n 'est pas sur le bord droit
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire; //(idem que precedemment)
				} 
			}

			else if(b.ori == Orientation.Verticale && s == Sens.Arriere && b.p1.y-1>=0) {  /// nouvelle pos n 'est pas sur le bord bas
				cases[b.p1.x][b.p1.y-1].vision = Vision.Claire; //(idem que precedemment)
				if(b.p1.x-1>0) {  /// nouvelle pos n 'est pas sur le bord gauche
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire; //(idem que precedemment)
				}
				if(b.p2.x+1<taille) {  /// nouvelle pos n 'est pas sur le bord droit
					cases[b.p1.x+1][b.p1.y-1].vision = Vision.Claire; //(idem que precedemment)
				}
			}
			else if(b.ori == Orientation.Horizontale && s == Sens.Rotation ) { /// passage de verticale a horizontale 
				for(int i= b.p1.x; i<= b.p2.x; i++) {
					cases[i][b.centre.y + 1].vision = Vision.Claire;
					cases[i][b.centre.y - 1].vision = Vision.Claire; 
				}
				if(b.p2.x+1<taille) { /// nouvelle pos n'est pas sur le bord droit 
					cases[b.p2.x+1][b.p2.y-1].vision = Vision.Claire;
					cases[b.p2.x+1][b.p2.y].vision = Vision.Claire; 
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire; 
				}
				if(b.p1.x-1>0) { /// nouvelle pos n'est pas sur le bord gauche
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire; 
					cases[b.p1.x-1][b.p1.y].vision = Vision.Claire; 
					cases[b.p1.x-1][b.p1.y+1].vision = Vision.Claire;
				}
			}
			else if(b.ori == Orientation.Verticale && s == Sens.Rotation ) { /// passage de horizontal a verticale
				for(int i= b.p1.y; i<= b.p2.y; i++) {
					cases[b.centre.x + 1][i].vision = Vision.Claire;
					cases[b.centre.x - 1][i].vision = Vision.Claire;
				}
				if(b.p2.y+1<taille) { /// nouvelle pos n'est pas sur le bord haut 
					cases[b.p2.x-1][b.p2.y+1].vision = Vision.Claire; 
					cases[b.p2.x][b.p2.y+1].vision = Vision.Claire; 
					cases[b.p2.x+1][b.p2.y+1].vision = Vision.Claire;
				}
				if(b.p1.y-1>0) { /// nouvelle pos n'est pas sur le bord bas
					cases[b.p1.x-1][b.p1.y-1].vision = Vision.Claire;
					cases[b.p1.x][b.p1.y-1].vision = Vision.Claire; 
					cases[b.p1.x+1][b.p1.y-1].vision = Vision.Claire;
				}
			}
		}

	}	



	public void tire(int x, int y, Joueur M, Joueur H) { //Fonction a utiliser seulement pour l'IA
		int ind_bat=0;
		if(cases[x][y].occupant == Proprio.Humain && M.nature == Proprio.Machine) {
			
			for(int i=H.listeBat.size()-1; i>=0; i--){	
				Bateaux b = H.listeBat.get(i);
				if(b == cases[x][y].bat) { //Si le bateau de la case correspond a un bateaux de la liste
					M.updateScore(); 
					b.updateVie();
					ind_bat=H.listeBat.indexOf(b); //Indice du bateaux touche
					M.updateScore();
					

					if(b.vie == 0) { //Si le bateau est mort
						for(int jj = b.p1.x; jj<=b.p2.x; jj++) {
							for(int ii = b.p1.y; ii<=b.p2.y; ii++) {
								cases[jj][ii].occupant = Proprio.Libre; //les cases precedemment occupe par le bateau deviennent libre
							}
						}
						int ind = b.numero;
						switch (ind) { // Desactivation du boutton correspondant au bateau mort
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
						// supression des donnees corespondant a ce bateau
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
	
}
