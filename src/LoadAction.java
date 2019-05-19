import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadAction {
	/*
	 * pour charger et afficher les scores
	 */
	public static String loadFile() {
		String scores = "";
		try{
			BufferedReader buf = new BufferedReader(new FileReader("BestGameEver_HIGHSCORE.txt"));
			String line = buf.readLine();
			while(line != null){
				scores = scores +"\n"+ line;
				System.out.println(line);
			line = buf.readLine();
			}
			buf.close();
			}
			catch (IOException e){
			e.printStackTrace();
			}
		return scores;
	}
}
