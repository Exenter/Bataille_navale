import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

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
		Point p = new Point();
		int vie =3;
		while(!IG.carte.listeBatHumain.isEmpty() && !IG.carte.listeBatMachine.isEmpty()){
			System.out.print(""); //Magic syso is magic
			
			if(IG.tour == 2){
				numB = IA.choixAlleaBateauDep();
				//s = IA.choixAlleaSensDep();
				while(!IG.carte.verifDepPossible(IG.carte.listeBatMachine.get(numB),s)){
					numB = IA.choixAlleaBateauDep();
					s = IA.choixAlleaSensDep();
				}				
				
				IG.carte.listeBatMachine.get(numB).deplacementBateau(s);
				IG.carte.updateCaseBateau(IG.carte.listeBatMachine.get(numB), s);
				IG.updateCaseMachine();
				for(int i=0;i<IG.taille;i++){
	            	for (int j=0;j<IG.taille;j++){
	                if(IG.carte.cases[i][j].vision == Vision.Claire && IG.carte.cases[i][j].occupant == Proprio.Libre)
	                	IG.setWaterState(1, i, j);
	            	}
				}
				
				if(s == Sens.Rotation){
					s = Sens.Avant;
					if(!IG.carte.verifDepPossible(IG.carte.listeBatMachine.get(numB),s)){
						s = Sens.Arriere;
					}
					IG.carte.listeBatMachine.get(numB).deplacementBateau(s);
					IG.carte.updateCaseBateau(IG.carte.listeBatMachine.get(numB), s);
					IG.updateCaseMachine();
					for(int i=0;i<IG.taille;i++){
		            	for (int j=0;j<IG.taille;j++){
		                if(IG.carte.cases[i][j].vision == Vision.Claire && IG.carte.cases[i][j].occupant == Proprio.Libre)
		                	IG.setWaterState(1, i, j);
				}}}
				p = IA.choixAleaPointTire();
				if ((IA.KillAllHumans % 2) ==0){
					int X = ThreadLocalRandom.current().nextInt(0,IG.homoSapiens.listePoint.size());
					p = IG.homoSapiens.listePoint.get(X);
				}
				
				
				if (IG.homoSapiens.listePoint.contains(p)){
					for(Bateaux b:IG.homoSapiens.listeBat){
						if((b.p1.x == p.x && b.p1.y == p.y) || (b.centre.x == p.x && b.centre.y == p.y) || (b.p2.x == p.x && b.p2.y == p.y)){
							vie = b.vie;
						}
					}
					int ind = IG.carte.cases[p.x][p.y].bat.numero;
					System.out.println("Bateau n°"+ ind+ "num bat: "+IG.carte.cases[p.x][p.y].bat.numero);
					IG.carte.tire(p.x, p.y, IG.IA, IG.homoSapiens);
					System.out.println("FIRE IN THE HALL");
					IG.remaining_hp(ind, IG.carte.cases[p.x][p.y].bat.vie);
				}
				IG.tour = 0;
			}
		}
		System.out.println("fini");
		if(IG.carte.listeBatMachine.isEmpty()){
      		JOptionPane.showMessageDialog(IG, "GG WP no re");
      	}
		else if(IG.carte.listeBatHumain.isEmpty()){
      		JOptionPane.showMessageDialog(IG, "YOU LOSE");
      	}
		
		

		
		
		
		
	}

}
