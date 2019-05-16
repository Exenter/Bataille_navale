import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;

public class SaveAction extends AbstractAction{
	
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
}
