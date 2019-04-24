import java.awt.event.ActionEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.AbstractAction;

public class SaveAction extends AbstractAction{
	
	public SaveAction(String texte){
		super(texte);
	}

	public void actionPerformed(ActionEvent e) { 
		try{
			FileOutputStream file = new FileOutputStream("BestGameEver(OrWeWishItWere).txt");
			ObjectOutputStream obj = new ObjectOutputStream(file);
			obj.writeObject(this);
			obj.flush();
			obj.close();
			}
			catch(Exception E){
				E.printStackTrace();
			}
	} 

}
