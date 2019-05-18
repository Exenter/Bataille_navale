
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
		}
		
		///tour////
		int numB = 15;
		Sens s = Sens.Avant;
		while(!IG.carte.listeBatHumain.isEmpty() && !IG.carte.listeBatMachine.isEmpty()){
			System.out.println(IG.tour);
			
			if(IG.tour == 2){
				System.out.println("je suis dans if");
				numB = IA.choixAlleaBateauDep();
				System.out.println("numB: "+ numB);
				//s = IA.choixAlleaSensDep();
				while(!IG.carte.verifDepPossible(IG.carte.listeBatMachine.get(numB),s)){
					numB = IA.choixAlleaBateauDep();
					s = IA.choixAlleaSensDep();
				}				
				
				IG.carte.listeBatMachine.get(numB).deplacementBateau(s);
				IG.carte.updateCaseBateau(IG.carte.listeBatMachine.get(numB), s);
				IG.updateCaseMachine();
				IG.tour = 0;
				
			}
			
		}
		
		

		
		
		
		
	}

}
