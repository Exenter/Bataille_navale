
public class PseudoPartie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Carte carte = new Carte();
		
		Joueur IA = new Joueur(Proprio.Machine,3,"BarbeBleu");
		Joueur Hsapien = new Joueur(Proprio.Humain, 3,"Jack_le_brave");
		
		
		InterfaceGraphique IG = new InterfaceGraphique(Hsapien, IA, carte); 
		IG.les_boutons();
		
		for(Bateaux b:Hsapien.listeBat) {
			IG.carte.addBateaux(b);
			IG.setCaseBateau(b);
			for(int i=0;i<carte.taille;i++){
		    	for (int j=0;j<carte.taille;j++){
					if(carte.cases[i][j].vision == Vision.Claire && carte.cases[i][j].occupant == Proprio.Libre)
			        	IG.setWaterState(1, i, j);
		    	}
			}	
		}
		
		for(Bateaux bm:IA.listeBat) {
			IG.carte.addBateaux(bm);
			//IG.setCaseBateau(bm);
		}
		
		
		
//		Point p = IA.choixAleaPointTire();
		//System.out.println(c.verifDepPossible(b4, Sens.Avant));
//		System.out.println("AVANT tire:");
//		System.out.println("etat case: "+ c.cases[p.x][p.y].vision+"  " +c.cases[p.x][p.y].occupant);
//		System.out.println("vie de b4: "+b4.vie);
//		System.out.println("vie de b4 dans la liste bateau: "+c.listeBatMachine.get(1).vie);
//		System.out.println("score du joueur: "+ IA.score);
//		System.out.println("vie bat de la case: " + c.cases[7][7].bat.vie);
		
//		System.out.println("Apres tire:");
//		c.tire(p.x, p.y, IA);
//		System.out.println("etat case: "+ c.cases[p.x][p.y].vision+"  "+c.cases[p.x][p.y].occupant);
//		System.out.println("vie de b4: "+b4.vie);
//		System.out.println("vie de b4 dans la liste bateau: "+c.listeBatMachine.get(1).vie);
//		System.out.println("score du joueur: "+ IA.score);
//		System.out.println("vie bat de la case: " + c.cases[7][7].bat.vie);
//		
//		for(int i=b1.p1.x; i<=b1.p2.x; i++) {
//			for(int j=b1.p1.y; j<=b1.p2.y; j++) {
//				System.out.println("i: "+i+" j: "+j);
//				System.out.println(c.cases[i][j].occupant);
//				System.out.println(c.cases[i][j].vision);
//				//System.out.println("\n");
//			}
//		}
//		
//		Sens s = Sens.Avant;
		
//		if(c.verifDepPossible(Hsapien.listeBat.get(0), s)) {
//			//System.out.println("eh beh");
//			Hsapien.listeBat.get(0).deplacementBateau(s);
//			//System.out.println("orientaion: "+b4.ori);
//			//System.out.println(c.cases[b4.p1.x-1][b4.p1.y].occupant);
//			c.updateCaseBateau(Hsapien.listeBat.get(0),s);
//			//System.out.println("orientaion: "+b4.ori);
//			//System.out.println(c.cases[b4.p1.x-1][b4.p1.y].occupant);
//			c.updateVisibilité(Hsapien.listeBat.get(0), s);
//			IG.setCaseBateau(Hsapien.listeBat.get(0));
//			
//		};
//	
//		System.out.println("\n //////// APRES DEP ///////////");
//		System.out.println("\n devrait etre libre et claire");
//		int i =3;
//		int j =2; 
//		System.out.println("i: "+i+" j: "+j);
//		System.out.println(c.cases[i][j].occupant);
//		System.out.println(c.cases[i][j].vision);
//		
//		//System.out.println("\n devrait etre claire");
//		i =3;
//		j =4; 
//		System.out.println("i: "+i+" j: "+j);
//		System.out.println(c.cases[i][j].occupant);
//		System.out.println(c.cases[i][j].vision);
//		
//		System.out.println("\n devrait etre Humain");
//		for( i=b1.p1.x; i<=b1.p2.x; i++) {
//			for( j=b1.p1.y; j<=b1.p2.y; j++) {
//				System.out.println("i: "+i+" j: "+j);
//				System.out.println(c.cases[i][j].occupant);
//				System.out.println(c.cases[i][j].vision);
//				//System.out.println("\n");
//			}
//		}
		

		
		
		
		
	}

}
