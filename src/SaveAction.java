import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;

public class SaveAction extends AbstractAction{
	/*
	 * appelable depuis le menu pour sauvegarder a tout moment le score du joueur
	 * methode save() appelee pour sauvegarder le score en fin de partie
	 */
	public SaveAction(String texte){
		super(texte);
	}

	public void actionPerformed(ActionEvent e) { 
		try{
			BufferedWriter fileLog = new BufferedWriter(new FileWriter(new File("BestGameEver_HIGHSCORE.txt"), true));
			fileLog.write(Joueur.nom+" : "+Joueur.score+"\n");
			fileLog.close();
			}
			catch (IOException a){
			a.printStackTrace();
			}
		}
	
	public static void save(Joueur j) {
		try{
			BufferedWriter fileLog = new BufferedWriter(new FileWriter(new File("BestGameEver_HIGHSCORE.txt"), true));
			fileLog.write(j.nom+" : "+j.score+"\n");
			fileLog.close();
			}
			catch (IOException a){
			a.printStackTrace();
			}
		}
}
