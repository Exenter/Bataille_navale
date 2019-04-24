
public class Bateaux {
	int taille; // impaire
	int vie;
	Proprio pro;
	
	Point p1;
	Point centre;
	Point p2;
	Orientation ori;
	
	//// CONSTRUCTEUR /////
public Bateaux(Point p1, Point centre, Point p2, Proprio pro ) {
		this.pro = pro;
		this.centre = centre;
		if(p1.x < p2.x || p1.y < p2.y) {
			this.p1 = p1;
			this.p2 = p2;
		}
		else {
			this.p1 = p2;
			this.p2 = p1;
		}

		if(p1.x == p2.x && p1.x == centre.x) {
			ori = Orientation.Verticale;
			taille = p2.y -p1.y;
		}
		else {
			ori = Orientation.Horizontale;
			taille = p2.x - p1.x;
		}
	}

	//// FONCTIONS /////
//fonction verif dep pour colision
	public void deplacementBateau(Sens sens) {

		switch (sens) {
		case Avant :
			if (ori == Orientation.Verticale) {
				p1.depX(sens);
				centre.depX(sens);
				p2.depX(sens);
			}
			else {
				p1.depY(sens);
				centre.depY(sens);
				p2.depY(sens);
			}
			
			break;
		case Arriere:
			if (ori == Orientation.Verticale) {
				p1.depX(sens);
				centre.depX(sens);
				p2.depX(sens);
			}
			else {
				p1.depY(sens);
				centre.depY(sens);
				p2.depY(sens);
			}
			
			break;
			
		case Rotation:
			if (ori == Orientation.Verticale) {
				p1.rotationX(centre, "+");
				p2.rotationX(centre, "-");
				ori = Orientation.Horizontale;
			}
			else {
				p1.rotationY(centre, "+");
				p2.rotationY(centre, "-");
				ori = Orientation.Verticale;
			}
			
			break;
			
		default:
			System.exit(0);
			break;
		}
		
	}
		
	
	
	
		
}

	
	//Methodes
	
	/* deplacement du bateau
	 * 		gestion des bord
	 * tire
	 * changement point de vie
	 */

