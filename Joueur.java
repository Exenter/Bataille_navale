import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Joueur {
	int nbrBatInitial;
	int score;
	Proprio nature;
	ArrayList<Bateaux> listeBat;
	ArrayList<Point> listePoint;
	
	public Joueur(Proprio nature, int nbrBatInitial) {
		this.nature = nature;
		score = 0;
		this.nbrBatInitial = nbrBatInitial;
		listeBat = new ArrayList<Bateaux>();		
		listePoint = new ArrayList<Point>();
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
			int X = ThreadLocalRandom.current().nextInt(0,15);
			int Y = ThreadLocalRandom.current().nextInt(0,15);
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
