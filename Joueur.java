import java.util.ArrayList;

public class Joueur {
	//int nbrBateau;
	int score;
	//ArrayList<Bateaux> listeBateaux;
	//ArrayList<Point> positionOccupe;
	Proprio nature;
	
	public Joueur(Proprio nature) {
		this.nature = nature;
		//nbrBateau = 0;
		score = 0;
		//listeBateaux = new ArrayList<Bateaux>();
		
	}
	
//	public void addBateau(Bateaux b) {
//		listeBateaux.add(b);
//	}
	
	
	
	public void updateScore() {
		score += 1;
	}
//	
//	public void updateNbrBateau(int modification) {
//		nbrBateau += modification; 
//	}
//	
	
	
	/* dans constructeur ajouter les bateaux dans la liste
	/* changement score
	 * chanchemant nbr de bateaux
	 * 
	 */
	
}
