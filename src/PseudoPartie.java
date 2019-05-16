
public class PseudoPartie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Carte c = new Carte();
		InterfaceGraphique IG = new InterfaceGraphique(); 
		IG.les_boutons();

		Point b1_p1 = new Point(3,2);
		Point b1_c = new Point(3,3);
		Point b1_p2 = new Point(3,4);
		Bateaux b_1 = new Bateaux(b1_p1, b1_c, b1_p2, Proprio.Humain, IG);
		c.addBateaux(b_1);
		b_1.setCaseBateau(Orientation.Verticale);
		
		Point b2_p1 = new Point(12,5);
		Point b2_c = new Point(13,5);
		Point b2_p2 = new Point(14,5);
		Bateaux b_2 = new Bateaux(b2_p1, b2_c, b2_p2, Proprio.Humain, IG);
		c.addBateaux(b_2);
		b_2.setCaseBateau(Orientation.Horizontale);
		
		Point b3_p1 = new Point(12,8);
		Point b3_c = new Point(12,9);
		Point b3_p2 = new Point(12,10);
		Bateaux b_3 = new Bateaux(b3_p1, b3_c, b3_p2, Proprio.Machine, IG);
		c.addBateaux(b_3);
		b_3.setCaseBateau(Orientation.Verticale);
		
		
		Point b4_p1 = new Point(5,7);
		Point b4_c = new Point(6,7);
		Point b4_p2 = new Point(7,7);
		Bateaux b_4 = new Bateaux(b4_p1, b4_c, b4_p2, Proprio.Machine, IG);
		c.addBateaux(b_4);
		b_4.setCaseBateau(Orientation.Horizontale);
		
		
//		for(int i=b_4.p1.x-1; i<=b_4.p2.x+1; i++) {
//			for(int j=b_4.p1.y-1; j<=b_4.p2.y+1; j++) {
//				System.out.println("i: "+i+" j: "+j);
//				System.out.println(c.cases[i][j].occupant);
//				System.out.println(c.cases[i][j].vision);
//			}
//		}	
		
	}

}
