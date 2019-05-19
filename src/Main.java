import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * bla bla bla on cree les trucs dont on a besoin
		 */
		Carte carte = new Carte();
		
		Joueur IA = new Joueur(Proprio.Machine,3,"BarbeBleu");
		Joueur Hsapien = new Joueur(Proprio.Humain, 3,"Jack_le_brave");
		
		
		InterfaceGraphique IG = new InterfaceGraphique(Hsapien, IA, carte); 
		IG.les_boutons();
		
		// -- place les bateaux sur la carte  -- //
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
		
		// -- Gestion des tours de l'IA -- //
		int numB = 15;
		Sens s = Sens.Avant;
		Point p = new Point();
		int vie =3;
		// le jeu se deroule tant qu'un des joueurs a encore des bateaux
		while(!IG.carte.listeBatHumain.isEmpty() && !IG.carte.listeBatMachine.isEmpty()){
			System.out.print(""); //Magic syso is magic
			
			if(IG.tour == 2){ // toute les deux actions du joueur, l'IA tir et deplace un bateau au hasard
				numB = IA.choixAlleaBateauDep();
				//s = IA.choixAlleaSensDep();
				while(!IG.carte.verifDepPossible(IG.carte.listeBatMachine.get(numB),s)){
					numB = IA.choixAlleaBateauDep();
					s = IA.choixAlleaSensDep();
				}				
				
				IG.carte.listeBatMachine.get(numB).deplacementBateau(s);
				IG.carte.updateCaseBateau(IG.carte.listeBatMachine.get(numB), s);
				IG.updateCaseMachine();
				// mise a jour de la position du bateau de l'IA pour l'affichage
				for(int i=0;i<IG.taille;i++){
	            	for (int j=0;j<IG.taille;j++){
	                if(IG.carte.cases[i][j].vision == Vision.Claire && IG.carte.cases[i][j].occupant == Proprio.Libre)
	                	IG.setWaterState(1, i, j);
	                if(IG.carte.cases[i][j].vision == Vision.Brouillard && IG.carte.cases[i][j].occupant == Proprio.Libre)
	                	IG.setWaterState(8, i, j);
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
		                if(IG.carte.cases[i][j].vision == Vision.Brouillard && IG.carte.cases[i][j].occupant == Proprio.Libre)
		                	IG.setWaterState(8, i, j);
				}}}
				/* l'IA va tirer de facon purement mechante sur un des bateaux du joueur toute les x actions (defini par modulo KillAllHumans)
				 * cela permet d'accelerer un peu la partie et de la rendre plus equitable
				 */
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
					// changement des pv du bateau cible par l'IA
					
				}
				IG.tour = 0;
			}
		}
		/*
		 * celui qui n'a plus de bateau perd la partie : le message est donc different
		 * affichage des High Scores en fin de partie sur le messageDialog, des qu'il est ferme le jeu est quitte
		 */
		if(IG.carte.listeBatMachine.isEmpty()){
			SaveAction.save(Hsapien);
			String scores = "HIGH SCORE\n"+LoadAction.loadFile();
      		JOptionPane.showMessageDialog(IG, scores, "GG WP no re", JOptionPane.PLAIN_MESSAGE);
      		IG.dispose();
      	}
		
		else if(IG.carte.listeBatHumain.isEmpty()){
			SaveAction.save(IA);
			String scores = LoadAction.loadFile();
      		JOptionPane.showMessageDialog(IG, scores, "YOU LOSE", JOptionPane.PLAIN_MESSAGE);
      		IG.dispose();
      	}
		
		

		
		
		
		
	}

}
