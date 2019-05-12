
public class Bateaux extends InterfaceGraphique{
	int taille; // impaire
	int vie;
	Proprio pro;
	int numero = 0;
	Point p1;
	Point centre;
	Point p2;
	Orientation ori;
	
	//// CONSTRUCTEUR /////
public Bateaux(Point p1, Point centre, Point p2, Proprio pro ) {
		
		this.pro = pro;
		this.centre = centre;
		numero += 1;
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
			vie = taille+1;
		}
		else {
			ori = Orientation.Horizontale;
			taille = p2.x - p1.x;
			vie = taille+1;
		}
	}

	//// FONCTIONS /////

	public void setCaseBateau(Orientation o){
		if (o == Orientation.Verticale){
			setWaterState(2, p1.x, p1.y);
			setWaterState(3, centre.x, centre.y);
			setWaterState(4, p2.x, p2.y);
		}
		else{
			setWaterState(5, p1.x, p1.y);
			setWaterState(6, centre.x, centre.y);
			setWaterState(7, p2.x, p2.y);
		}
	}	
	
	public void deplacementBateau(Sens sens) {

		switch (sens) {
		case Avant :
			if (ori == Orientation.Horizontale) {
				p1.depX(sens);
				//System.out.println("class bateau coordoné x init b.centre: "+ centre.x);
				centre.depX(sens);
				//System.out.println("class bateau coordoné x new b.centre: "+ centre.x);
				p2.depX(sens);
			}
			else {
				p1.depY(sens);
				centre.depY(sens);
				p2.depY(sens);
			}
			
			break;
		case Arriere:
			if (ori == Orientation.Horizontale) {
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
				p1.rotationX(centre, "-");
				p2.rotationX(centre, "+");
				p1.y = centre.y;
				p2.y = centre.y;
				ori = Orientation.Horizontale;
			}
			else {
				p1.rotationY(centre, "-");
				p2.rotationY(centre, "+");
				p1.x = centre.x;
				p2.x = centre.x;
				ori = Orientation.Verticale;
			}
			
			break;
			
		default:
			System.exit(0);
			break;
		}
		
	}
		
	
	public void updateVie() {
		
			vie = vie - 1;
		
	}
	
		
}

	
	//Methodes
	
	/* tire
	 * changement point de vie
	 */

