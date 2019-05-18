import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Joueur {
	int nbrBatInitial;
	static int score;
	static String nom;
	Proprio nature;
	ArrayList<Bateaux> listeBat;
	ArrayList<Point> listePoint;
	
	public Joueur(Proprio nature, int nbrBatInitial, String nom) {
		this.nature = nature;
		score = 0;
		this.nbrBatInitial = nbrBatInitial;
		this.nom = nom;
		listeBat = new ArrayList<Bateaux>();		
		listePoint = new ArrayList<Point>();
		if(nature == Proprio.Humain) {
			Point b1_p1 = new Point(3,2);
			Point b1_c = new Point(3,3);
			Point b1_p2 = new Point(3,4);
			Bateaux b_1 = new Bateaux(b1_p1, b1_c, b1_p2, Proprio.Humain);
			addBateaux(b_1);
			
			Point b2_p1 = new Point(7,17);
			Point b2_c = new Point(8,17);
			Point b2_p2 = new Point(9,17);
			Bateaux b_2 = new Bateaux(b2_p1, b2_c, b2_p2, Proprio.Humain);
			addBateaux(b_2);
			
			Point b3_p1 = new Point(17,11);
			Point b3_c = new Point(17,12);
			Point b3_p2 = new Point(17,13);
			Bateaux b_3 = new Bateaux(b3_p1, b3_c, b3_p2, Proprio.Humain);
			addBateaux(b_3);
			
		}
		else {
			Point b4_p1 = new Point(3,10);
			Point b4_c = new Point(4,10);
			Point b4_p2 = new Point(5,10);
			Bateaux b_4 = new Bateaux(b4_p1, b4_c, b4_p2, Proprio.Machine);
			addBateaux(b_4);
			
			Point b5_p1 = new Point(14,16);
			Point b5_c = new Point(14,17);
			Point b5_p2 = new Point(14,18);
			Bateaux b_5 = new Bateaux(b5_p1, b5_c, b5_p2, Proprio.Machine);
			addBateaux(b_5);
			
			Point b6_p1 = new Point(16,1);
			Point b6_c = new Point(17,1);
			Point b6_p2 = new Point(18,1);
			Bateaux b_6 = new Bateaux(b6_p1, b6_c, b6_p2, Proprio.Machine);
			addBateaux(b_6);
			
		}	
	}
	
	
	public void updateScore() {
		score += 1;
	}
	
	
	public void addBateaux(Bateaux b) {
		listeBat.add(b);
		listePoint.add(b.centre);
		listePoint.add(b.p1);
		listePoint.add(b.p2);
	}
	
	public int choixAlleaBateauDep() {
		///int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		int indiceRandomBat = ThreadLocalRandom.current().nextInt(0,nbrBatInitial);
		return indiceRandomBat;
	}
	
	public Sens choixAlleaSensDep() {
		Sens s = Sens.Avant;
		
		int indiceSensAllea = ThreadLocalRandom.current().nextInt(0,3);
		switch (indiceSensAllea) {
		case 0:
			s = Sens.Avant;
			break;
		case 1:
			s = Sens.Arriere;
			break;
		case 2:
			s = Sens.Rotation;
			break;
		default:
			System.exit(0);
			break;
		}
				
		return s;
	}
	

	public Point choixAleaPointTire() {
		boolean tirePoss = false;
		Point p = new Point();
		while(!tirePoss ) {
			int X = ThreadLocalRandom.current().nextInt(0,InterfaceGraphique.taille);
			int Y = ThreadLocalRandom.current().nextInt(0,InterfaceGraphique.taille);
			p = new Point(X, Y);
			//System.out.println("coo point alea: " +p.x+" "+p.y);
			int i=0;
			boolean trouve =false;
			while(i<listePoint.size() && !trouve ) {
				//System.out.println("check");
				Point d = listePoint.get(i);
				if(p.x == d.x && p.y == d.y) {
					trouve= true;
				}
				i += 1;
			}
			if(trouve==false) {
				//System.out.println("oui");
				tirePoss = true;// passage de tirePoss en true
			}
			
		}
		System.out.println(tirePoss);

		return p;
	}
}
