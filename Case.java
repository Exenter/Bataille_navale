
public class Case {
	Proprio occupant;
	Vision vision;
	Bateaux bat;
	
	public Case() {
		occupant = Proprio.Libre;
		vision = Vision.Brouillard;
	}
	
	public void essay(Bateaux b) {
		if(occupant == Proprio.Humain) {
			bat = b;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p1 = new Point(5,5);
		Point centre = new Point(5, 10);
		Point p2 = new Point(5,15);
		Bateaux A = new Bateaux(p1, centre, p2, Proprio.Humain);
		
		Case c = new Case();
		System.out.println(c.occupant);
		c.occupant = Proprio.Humain;
		c.essay(A);
		System.out.println(c.occupant);
		System.out.println(c.bat.p1.x);
		System.out.println(c.bat.taille);
		
	}

	
	
	
	
}
