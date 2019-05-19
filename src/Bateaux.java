

public class Bateaux{
	int taille; // impaire = a 3 pour l'instant
	int vie; 
	Proprio pro; // Bateaux apartient a Machine ou Humain
	int numero;
	Point p1;
	Point centre;
	Point p2;
	Orientation ori; // Orientation du bateaux sur la carte
	InterfaceGraphique gui;
	
	//// CONSTRUCTEUR /////
public Bateaux(Point p1, Point centre, Point p2, Proprio pro, InterfaceGraphique x, int numero) {
		gui = x;
		this.pro = pro;
		this.centre = centre;
		this.numero = numero;
		if(p1.x < p2.x || p1.y < p2.y) { // les coordonées de p1 sont toujours inferieurs celles de p2
			this.p1 = p1;
			
			this.p2 = p2;
		}
		else {
			this.p1 = p2;
			this.p2 = p1;
		}

		if(p1.x == p2.x && p1.x == centre.x) { // Defini l'orientation et la taille en fonction des coordonées des points
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

public Bateaux(Point p1, Point centre, Point p2, Proprio pro, int numero ) { // Constructeur sans interface
	gui = null;
	this.pro = pro;
	this.centre = centre;
	this.numero= numero;
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
	
	public void deplacementBateau(Sens sens) {
		switch (sens) { // Dependament de l'orientation du bateaux et du sens de deplacement -> deplacer les points du bateaux
		case Avant :
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
			if (vie < 0)
				vie =0;
	}
	
		
}

	
	
